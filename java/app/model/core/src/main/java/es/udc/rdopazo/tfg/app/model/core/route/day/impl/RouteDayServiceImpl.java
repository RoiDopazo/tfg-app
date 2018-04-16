package es.udc.rdopazo.tfg.app.model.core.route.day.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.core.route.day.RouteDayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.dao.RouteDayDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.JpaRouteDay;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

@Service
public class RouteDayServiceImpl<R extends Route<D, ?>, D extends RouteDay<S>, S extends Stay<?, ?, ?>>
        implements RouteDayService<R, D> {

    @Autowired
    RouteDayDao<D> dao;

    @Autowired
    RouteService<R> routeService;

    @Transactional
    public D add(R route) {
        D day = (D) new JpaRouteDay();
        day.setStartTime(32400000L);
        day.setRealTimeData(null);
        route.addDay(day);
        this.dao.add(day);
        return day;
    }

    @Transactional
    public D add(R route, Long startTime, String realTimeData) {
        D day = (D) new JpaRouteDay();
        day.setStartTime(startTime);
        day.setRealTimeData(realTimeData);
        route.addDay(day);
        this.dao.add(day);
        return day;
    }

    @Transactional
    public List<D> createDays(R route, Integer numDays) throws InstanceNotFoundException {
        List<D> days = new ArrayList<D>();

        Long lastDay = (long) route.getNumDays();
        for (int i = route.getNumDays(); i < numDays; i++) {
            D day = this.add(route);
            days.add(day);
        }
        for (int j = numDays; j < route.getNumDays(); j++) {
            this.delete(route.getId(), lastDay);
            lastDay -= 1L;
        }
        return days;
    }

    @Transactional
    public D update(D day) {
        this.dao.update(day);
        return day;
    }

    @Transactional
    public void delete(Long idRoute, Long idDay) throws InstanceNotFoundException {
        this.dao.remove(this.getById(idRoute, idDay));
    }

    public List<D> getAll(Long idRoute, Integer index, Integer count) {
        return this.dao.getAll(idRoute, index, count);
    }

    public List<D> getAll(Integer index, Integer count) {
        return this.dao.getAll(index, count);
    }

    public List<D> getByField(String field, String value, Integer index, Integer count)
            throws InputValidationException {
        if (!(field.equals("")) && !(value.equals(""))) {
            return this.dao.getListByField(field, value, index, count);
        } else {
            return this.dao.getAll(index, count);
        }
    }

    public D getById(Long idRoute, Long idDay) throws InstanceNotFoundException {
        D rotueDay = this.dao.getById(idRoute, idDay);
        if (rotueDay == null) {
            throw new InstanceNotFoundException(idRoute, "RouteDay not found");
        }
        return rotueDay;
    }

    public List<Long> getListDaysByRotueAndPlace(Long idRoute, String idFoursquare) {

        List<D> days = this.dao.getAll(idRoute);
        List<Long> listDays = new ArrayList<Long>();
        for (D d : days) {
            List<S> stays = d.getStays();
            for (S stay : stays) {
                if ((stay.getPlace() != null) && stay.getPlace().getIdFoursquare().equals(idFoursquare)) {
                    listDays.add(d.getDiaPK().getIdDay());
                }
            }
        }
        return listDays;
    }

    public List<D> getByFields(Long idRoute, String filter, Object value, Integer index, Integer count) {
        Map<String, Object> fields = new HashMap<String, Object>();

        if (idRoute != null) {
            fields.put("route-id", idRoute);
        }

        if (!(filter.equals("")) && !(value.equals(""))) {
            if (filter.equals("idDay")) {
                fields.put("diaPK-idDay", value);
            } else {
                fields.put(filter, value);
            }
        }
        return this.dao.getListByFields(fields, index, count);
    }

}
