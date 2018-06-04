package es.udc.rdopazo.tfg.app.model.core.route.impl;

import java.util.ArrayList;
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
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.dao.RouteDayDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.dao.StayDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.JpaRouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.util.OrderingType;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.UnUpdateableRouteException;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.RouteState;

@Service
public class RouteServiceImpl<R extends Route<D, ?>, D extends RouteDay<?>, S extends Stay<D, ?, ?>>
        implements RouteService<R, D, S> {

    @Autowired
    RouteDao<R> routeDao;

    @Autowired
    RouteDayDao<D> routeDayDao;

    @Autowired
    StayDao<S> stayDao;

    private void checkRouteDayUpdateable(R route) throws UnUpdateableRouteException {
        if (route != null) {
            if ((route.getState() != null) && (route.getState() != RouteState.PENDING)) {
                throw new UnUpdateableRouteException("Route", route.getState().toString());
            }
        }
    }

    private void checkRouteStayUpdateable(R route) throws UnUpdateableRouteException {
        if (route != null) {
            if ((route.getState() != null) && (route.getState() == RouteState.COMPLETED)) {
                throw new UnUpdateableRouteException("Route", route.getState().toString());
            }
        }

    }

    public List<R> getAllRoutes(Integer index, Integer count) {
        return this.routeDao.getAll(index, count);
    }

    @Transactional
    public R getRouteById(Long id) throws InstanceNotFoundException {
        R r = this.routeDao.getById(id);
        if (r != null) {
            return r;
        } else {
            throw new InstanceNotFoundException(id, "Route not found");
        }
    }

    @Transactional
    public R addRoute(R route) {
        route.setDistance(0L);
        route.setTime(0L);
        route.setNumDays(0);
        route.setNumPlaces(0);
        route.setState(RouteState.PENDING);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        route.setCreationDate(date);
        this.routeDao.add(route);
        return route;
    }

    @Transactional
    public R updateRoute(R route) throws UnUpdateableRouteException {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        if ((route.getStartDate() != null) && (route.getStartDate().before(date))) {
            if ((route.getEndDate() != null) && (route.getEndDate().before(date))) {
                route.setState(RouteState.COMPLETED);
            } else {
                route.setState(RouteState.IN_PROGRESS);
            }
        } else {
            route.setState(RouteState.PENDING);
        }
        this.routeDao.update(route);
        return route;
    }

    @Transactional
    public R updateRoutePriv(R route) {
        this.routeDao.update(route);
        return route;
    }

    @Transactional
    public void deleteRoute(Long id) throws InstanceNotFoundException {
        this.routeDao.remove(this.getRouteById(id));
    }

    public List<R> getRoutesByField(String field, Object value, Integer index, Integer count) {
        if (!(field.equals("")) && !(value.equals(""))) {
            return this.routeDao.getListByField(field, value, index, count);
        } else {
            return this.routeDao.getAll(index, count);
        }
    }

    public List<R> getRoutesByFields(Long idUser, String filter, Object value, Integer index, Integer count) {
        Map<String, Object> fields = new HashMap<String, Object>();
        if (idUser != null) {
            fields.put("user-id", idUser);
        }

        if (filter.equals("state")) {
            try {
                String valueStr = (String) value;
                RouteState state = RouteState.valueOf(valueStr.toUpperCase());
                fields.put(filter, state);
            } catch (Exception e) {
                return new ArrayList<R>();
            }
        } else if (filter.equals("priv")) {
            try {
                String valueStr = (String) value;
                Boolean priv = Boolean.parseBoolean(valueStr);
                fields.put(filter, priv);
            } catch (Exception e) {
                return new ArrayList<R>();
            }
        } else if (!(filter.equals("")) && !(value.equals(""))) {
            fields.put(filter, value);
        }

        return this.routeDao.getListByFields(fields, index, count);
    }

    public List<R> exploreRoutes(Long idUser, String city, String state, Long numDays, Long maxDistance,
            Long maxDuration, Integer index, Integer count) {

        RouteState stateObj = null;

        if (!state.equals("")) {
            try {
                stateObj = RouteState.valueOf(state.toUpperCase());
            } catch (Exception e) {
                return new ArrayList<R>();
            }
        }

        return this.routeDao.explore(idUser, city, stateObj, numDays, maxDistance, maxDuration, index, count);

    }

    @Transactional
    public D addRouteDay(R route) throws UnUpdateableRouteException {
        this.checkRouteDayUpdateable(route);
        D day = (D) new JpaRouteDay();
        day.setStartTime(32400000L);
        day.setRealTimeData(null);
        route.addDay(day);
        this.routeDayDao.add(day);
        return day;
    }

    @Transactional
    public D addRouteDay(R route, Long startTime, String realTimeData) throws UnUpdateableRouteException {
        this.checkRouteDayUpdateable(route);
        D day = (D) new JpaRouteDay();
        day.setStartTime(startTime);
        day.setRealTimeData(realTimeData);
        route.addDay(day);
        this.routeDayDao.add(day);
        return day;
    }

    @Transactional
    public List<D> createRouteDays(R route, Integer numDays)
            throws InstanceNotFoundException, UnUpdateableRouteException {
        List<D> days = new ArrayList<D>();

        Long lastDay = (long) route.getNumDays();
        for (int i = route.getNumDays(); i < numDays; i++) {
            D day = this.addRouteDay(route);
            days.add(day);
        }
        for (int j = numDays; j < route.getNumDays(); j++) {
            this.deleteRouteDay(route.getId(), lastDay);
            lastDay -= 1L;
        }
        return days;
    }

    @Transactional
    public D updateRouteDay(D day) {
        this.routeDayDao.update(day);
        return day;
    }

    @Transactional
    public void deleteRouteDay(Long idRoute, Long idDay) throws InstanceNotFoundException, UnUpdateableRouteException {
        D day = this.getRouteDayById(idRoute, idDay);
        this.checkRouteDayUpdateable((R) day.getRoute());
        this.routeDayDao.remove(day);
    }

    public List<D> getAllRouteDays(Long idRoute, Integer index, Integer count) {
        return this.routeDayDao.getAll(idRoute, index, count, OrderingType.ASC);
    }

    public List<D> getAllRouteDays(Integer index, Integer count) {
        return this.routeDayDao.getAll(index, count);
    }

    public List<D> getRouteDaysByField(String field, Object value, Integer index, Integer count) {
        if (!(field.equals("")) && !(value.equals(""))) {
            return this.routeDayDao.getListByField(field, value, index, count);
        } else {
            return this.routeDayDao.getAll(index, count);
        }
    }

    public D getRouteDayById(Long idRoute, Long idDay) throws InstanceNotFoundException {
        D rotueDay = this.routeDayDao.getById(idRoute, idDay);
        if (rotueDay == null) {
            throw new InstanceNotFoundException(idRoute, "RouteDay not found");
        }
        return rotueDay;
    }

    @Transactional
    public List<Long> getRouteDaysByRotueAndPlace(Long idRoute, String idFoursquare) {

        List<D> days = this.routeDayDao.getAll(idRoute);
        List<Long> listDays = new ArrayList<Long>();
        for (D d : days) {
            List<Stay> stays = (List<Stay>) d.getStays();
            for (Stay stay : stays) {
                if ((stay.getPlace() != null) && stay.getPlace().getIdFoursquare().equals(idFoursquare)) {
                    listDays.add(d.getDiaPK().getIdDay());
                }
            }
        }
        return listDays;
    }

    public List<D> getRouteDaysByFields(Long idRoute, String filter, Object value, Integer index, Integer count) {
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
        return this.routeDayDao.getListByFields(fields, index, count);
    }

    public List<S> getAllStays(Integer index, Integer count) {
        return this.stayDao.getAll(index, count);
    }

    public List<S> getStaysByField(String field, String value, Integer index, Integer count) {
        if (!(field.equals("")) && !(value.equals(""))) {
            return this.stayDao.getAll(index, count);
        } else {
            return this.stayDao.getListByField(field, value, index, count);
        }
    }

    public List<S> getStaysByFields(Long idRoute, Long idDay, String filter, String value, Integer index,
            Integer count) {
        Map<String, Object> fields = new HashMap<String, Object>();
        if ((idRoute != null) && (idDay != null)) {
            D day = this.routeDayDao.getById(idRoute, idDay);
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
        return this.stayDao.getListByFields(fields, index, count);
    }

    // Ordered
    public List<S> getAllStaysInDay(Long idRoute, Long idDay) {
        D day = this.routeDayDao.getById(idRoute, idDay);
        return this.stayDao.getListByField("day", day, OrderingType.ASC, "order");
    }

    public List<S> getStaysByRouteDayAndPlace(Long idRoute, Long idDay, Long idPlace) {

        Map<String, Object> fields = new HashMap<String, Object>();
        D day = this.routeDayDao.getById(idRoute, idDay);
        fields.put("day", day);
        fields.put("place", idPlace);
        return this.stayDao.getListByFields(fields);
    }

    public S getStayById(Long id) throws InstanceNotFoundException {
        S stay = this.stayDao.getById(id);
        if (stay != null) {
            return stay;
        } else {
            throw new InstanceNotFoundException(id, "Stay not found");
        }
    }

    @Transactional
    public S addStay(S dayPlace) throws UnUpdateableRouteException {
        this.checkRouteStayUpdateable((R) dayPlace.getDay().getRoute());
        dayPlace.setTime(0L);
        dayPlace.setTravelDistance(0L);
        dayPlace.setTravelTime(0L);
        dayPlace.setTravelMode("WALKING");
        this.stayDao.add(dayPlace);
        return dayPlace;
    }

    @Transactional
    public void deleteStay(Long id) throws InstanceNotFoundException, UnUpdateableRouteException {
        S stay = this.getStayById(id);
        this.checkRouteStayUpdateable((R) stay.getDay().getRoute());
        this.stayDao.remove(stay);

    }

    @Transactional
    public S updateStay(S dayPlace) throws UnUpdateableRouteException {
        this.checkRouteStayUpdateable((R) dayPlace.getDay().getRoute());
        this.stayDao.update(dayPlace);
        return dayPlace;
    }

    // Obtiene el mayor valor del orden entre los lugares de un dia (usado para fijar el orden al insertar)
    public Integer getStayMaxOrderNum(Long idRoute, Long idDay) {
        return (this.getAllStaysInDay(idRoute, idDay).size() + 1);
    }

    @Transactional
    public void fixStaysOrdersAfterDelete(Long idRoute, Long idDay) throws UnUpdateableRouteException {
        List<S> dayPlaces = this.getAllStaysInDay(idRoute, idDay);
        int index = 1;
        for (S dayPlace : dayPlaces) {
            dayPlace.setOrder(index);
            index++;
            this.updateStay(dayPlace);
        }
    }

}
