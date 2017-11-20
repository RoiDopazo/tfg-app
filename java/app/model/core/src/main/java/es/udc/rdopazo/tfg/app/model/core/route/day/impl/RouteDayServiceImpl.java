package es.udc.rdopazo.tfg.app.model.core.route.day.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.route.day.RouteDayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.DiaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.dao.RouteDayDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.JpaRouteDay;

@Service
public class RouteDayServiceImpl<R extends Route<D>, D extends RouteDay<DL>, DL extends DiaLugar<?, ?>>
        implements RouteDayService<R, D> {

    @Autowired
    RouteDayDao<D> dao;

    @Transactional
    public D add(R route, D day) {
        day.setOrder(0L);
        day.setStartTime(32400000L);
        route.addDay(day);
        this.dao.add(day);
        return day;
    }

    public List<D> createDays(R route, Integer numDays) {
        List<D> days = new ArrayList<D>();
        for (int i = 0; i < numDays; i++) {
            @SuppressWarnings("unchecked")
            D day = (D) new JpaRouteDay();
            this.add(route, day);
            days.add(day);
        }
        return days;
    }

    @Transactional
    public D update(D day) {
        this.dao.update(day);
        return day;
    }

    public void delete(Long idRoute, Long idDay) {
        this.dao.remove(this.dao.getById(idRoute, idDay));
    }

    public List<D> getAll(Long idRoute, Integer index, Integer count) {
        return this.dao.getAll(idRoute, index, count);
    }

    public D getById(Long idRoute, Long idDay) {
        return this.dao.getById(idRoute, idDay);
    }

    public List<Long> getListDaysByRotueAndPlace(Long idRoute, String idFoursquare) {

        List<D> days = this.dao.getAll(idRoute);
        List<Long> listDays = new ArrayList<Long>();
        for (D d : days) {
            List<DL> dayPlaces = d.getDayPlaces();
            for (DL dayPlace : dayPlaces) {
                if (dayPlace.getPlace().getIdFoursquare().equals(idFoursquare)) {
                    listDays.add(d.getDiaPK().getIdDay());
                }
            }
        }
        return listDays;
    }

}
