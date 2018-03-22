package es.udc.rdopazo.tfg.app.model.core.stay.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.stay.StayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.dao.RouteDayDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.dao.StayDao;
import es.udc.rdopazo.tfg.app.model.persistence.util.OrderingType;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

@Service
public class StayServiceImpl<R extends Route<D, ?>, D extends RouteDay<?>, S extends Stay<?, ?, ?>>
        implements StayService<S> {

    @Autowired
    private StayDao<S> dao;

    @Autowired
    private RouteDayDao<D> diaDao;

    public List<S> getAll(Integer index, Integer count) {
        return this.dao.getAll(index, count);
    }

    public List<S> getByField(String field, String value, Integer index, Integer count) {
        if (!(field.equals("null")) && !(value.equals("null"))) {
            return this.dao.getAll(index, count);
        } else {
            return this.dao.getListByField(field, value, index, count);
        }
    }

    public List<S> getByFields(Long idRoute, Long idDay, String filter, String value, Integer index, Integer count) {
        Map<String, Object> fields = new HashMap<String, Object>();
        if ((idRoute != null) && (idDay != null)) {
            D day = this.diaDao.getById(idRoute, idDay);
            fields.put("day", day);
        } else {
            if (idRoute != null) {
                fields.put("day-diaPK-idRoute", idRoute);
            }
        }

        if (!(filter.equals("")) && !(value.equals(""))) {
            if (filter.equals("idPlace")) {
                fields.put("place-id", value);
            } else if (filter.equals("idEventPlace")) {
                fields.put("eventPlace-id", value);
            } else {
                fields.put(filter, value);
            }

        }
        return this.dao.getListByFields(fields, index, count);
    }

    // Ordered
    public List<S> getAllInDay(Long idRoute, Long idDay) {
        D day = this.diaDao.getById(idRoute, idDay);
        return this.dao.getListByField("day", day, OrderingType.ASC, "order");
    }

    public List<S> getByRouteAndDayAndPlace(Long idRoute, Long idDay, Long idPlace) {

        Map<String, Object> fields = new HashMap<String, Object>();
        D day = this.diaDao.getById(idRoute, idDay);
        fields.put("day", day);
        fields.put("place", idPlace);
        return this.dao.getListByFields(fields);
    }

    public S getById(Long id) throws InstanceNotFoundException {
        S stay = this.dao.getById(id);
        if (stay != null) {
            return stay;
        } else {
            throw new InstanceNotFoundException(id, "Stay not found");
        }
    }

    @Transactional
    public S add(S dayPlace) {
        dayPlace.setTime(0L);
        dayPlace.setTravelDistance(0L);
        dayPlace.setTravelTime(0L);
        dayPlace.setTravelMode("WALKING");
        this.dao.add(dayPlace);
        return dayPlace;
    }

    @Transactional
    public void delete(Long id) throws InstanceNotFoundException {
        this.dao.remove(this.getById(id));

    }

    @Transactional
    public S update(S dayPlace) {
        this.dao.update(dayPlace);
        return dayPlace;
    }

    // Obtiene el mayor valor del orden entre los lugares de un dia (usado para fijar el orden al insertar)
    public Integer getMaxOrderNum(Long idRoute, Long idDay) {
        return (this.getAllInDay(idRoute, idDay).size() + 1);
    }

    @Transactional
    public void fixOrdersAfterDelete(Long idRoute, Long idDay) {
        List<S> dayPlaces = this.getAllInDay(idRoute, idDay);
        int index = 1;
        for (S dayPlace : dayPlaces) {
            dayPlace.setOrder(index);
            index++;
            this.update(dayPlace);
        }
    }

}
