package es.udc.rdopazo.tfg.app.model.core.route.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.dao.RouteDao;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.RouteState;

@Service
public class RouteServiceImpl<R extends Route<?, ?>> implements RouteService<R> {

    @Autowired
    RouteDao<R> dao;

    public List<R> getAll(Integer index, Integer count) {
        return this.dao.getAll(index, count);
    }

    @Transactional
    public R getById(Long id) throws InstanceNotFoundException {
        R r = this.dao.getById(id);
        if (r != null) {
            if (r.getEndDate() != null) {
                Date d = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(r.getEndDate());
                calendar.add(Calendar.HOUR, 24);
                if (calendar.getTime().before(d)) {
                    r = this.updateState(r, RouteState.COMPLETED);
                } else if (r.getStartDate().before(d)) {
                    r = this.updateState(r, RouteState.IN_PROGRESS);
                }
            }
            return r;
        } else {
            throw new InstanceNotFoundException(id, "Route not found");
        }

    }

    @Transactional
    public R add(R ruta) {
        ruta.setDistance(0L);
        ruta.setTime(0L);
        ruta.setNumDays(0);
        ruta.setNumPlaces(0);
        ruta.setState(RouteState.PENDING);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        ruta.setCreationDate(date);
        this.dao.add(ruta);
        return ruta;
    }

    @Transactional
    public R update(R ruta) {
        this.dao.update(ruta);
        return ruta;
    }

    @Transactional
    public void delete(Long id) throws InstanceNotFoundException {
        this.dao.remove(this.getById(id));
    }

    public List<R> getByField(String field, String value, Integer index, Integer count)
            throws InputValidationException {
        try {
            return this.dao.getListByField(field, value, index, count);
        } catch (Exception e) {
            throw new InputValidationException("Cant filter by field:" + field);
        }
    }

    @Transactional
    private R updateState(R route, RouteState state) {
        route.setState(state);
        this.dao.update(route);
        return route;
    }

    public List<R> getByFields(Long idUser, String filter, Object value, Integer index, Integer count) {
        Map<String, Object> fields = new HashMap<String, Object>();
        if (idUser != null) {
            fields.put("user-id", idUser);
        }

        if (!(filter.equals("")) && !(value.equals(""))) {
            fields.put(filter, value);
        }
        return this.dao.getListByFields(fields, index, count);
    }

}
