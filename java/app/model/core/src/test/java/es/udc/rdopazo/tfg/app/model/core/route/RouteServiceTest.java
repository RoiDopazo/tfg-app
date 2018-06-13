package es.udc.rdopazo.tfg.app.model.core.route;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import es.udc.rdopazo.tfg.app.model.core.BaseTest;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.dao.RouteDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.dao.RouteDayDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.dao.StayDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.JpaRoute;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.place.JpaStayPlace;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.UnUpdateableRouteException;

public class RouteServiceTest<U extends Usuario, R extends Route<D, U>, D extends RouteDay<S>, S extends Stay<D, ?, ?>>
        extends BaseTest {

    @Autowired
    private RouteService<R, D, S> service;

    @Autowired
    private RouteDao<R> routeDao;

    @Autowired
    private RouteDayDao<D> routeDayDao;

    @Autowired
    private StayDao<S> stayDao;

    private R insertValidRoute(String city, Boolean priv) {
        R route = (R) new JpaRoute();
        route.setCity(city);
        route.setPriv(priv);
        return this.service.addRoute(route);
    }

    private D insertValidRouteDay(R route, Long startTime) throws UnUpdateableRouteException {
        return this.service.addRouteDay(route, startTime, null);
    }

    private S insertValidStay(D day) throws UnUpdateableRouteException {
        S stay = (S) new JpaStayPlace();
        stay.setOrder(this.service.getStayMaxOrderNum(day.getDiaPK().getIdRoute(), day.getDiaPK().getIdDay()));
        stay.setDay(day);
        return this.service.addStay(stay);
    }

    @Transactional
    @Test
    public void testGetAllRoutes() {
        try {
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            List<R> routes = this.service.getAllRoutes(null, null);
            assertEquals(routes.size(), 2);

            routes = this.service.getAllRoutes(0, 1);
            assertEquals(routes.size(), 1);
        } finally {
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetRouteById() throws InstanceNotFoundException {
        try {
            Boolean exc = false;
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            R route = this.service.getRouteById(route2.getId());
            assertEquals(route.getCity(), "city2");

            try {
                this.service.getRouteById(0L);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }

            assertTrue(exc);

        } finally {
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testUpdateRoute() throws UnUpdateableRouteException, InstanceNotFoundException {
        try {
            Boolean exc = false;
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            route1.setCity("cityUp");
            this.service.updateRoute(route1);
            route1 = this.service.getRouteById(route1.getId());

            assertEquals(route1.getCity(), "cityUp");

        } finally {
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testDeleteRoute() throws InstanceNotFoundException {
        try {
            Boolean exc = false;
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);

            this.service.deleteRoute(route1.getId());
            List<R> routes = this.service.getAllRoutes(null, null);
            assertEquals(routes.size(), 1);

            try {
                this.service.deleteRoute(0L);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }

            assertTrue(exc);

        } finally {
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetRoutesByField() {
        try {
            Boolean exc = false;
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);

            List<R> routes = this.service.getRoutesByField("city", "city1", null, null);
            assertEquals(routes.size(), 1);

            routes = this.service.getRoutesByField("", "", null, null);
            assertEquals(routes.size(), 2);

        } finally {
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetRoutesByFields() {
        try {
            Boolean exc = false;
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);

            List<R> routes = this.service.getRoutesByFields(null, "state", "PENDING", null, null);
            assertEquals(routes.size(), 2);

            routes = this.service.getRoutesByFields(null, "state", "AAAA", null, null);
            assertEquals(routes.size(), 0);

            routes = this.service.getRoutesByFields(null, "", "", null, null);
            assertEquals(routes.size(), 2);

        } finally {
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testExploreRoutes() {
        try {
            Boolean exc = false;
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);

            List<R> routes = this.service.exploreRoutes(null, "city1", "", null, null, null, null, null);
            assertEquals(routes.size(), 1);

            routes = this.service.exploreRoutes(null, "city1", "AAA", null, null, null, null, null);
            assertEquals(routes.size(), 0);

        } finally {
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testAddRouteDayR() throws UnUpdateableRouteException {
        try {
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day = this.service.addRouteDay(route1);

            assertEquals(day.getDiaPK().getIdRoute(), route1.getId());

        } finally {
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testCreateRouteDays() throws UnUpdateableRouteException, InstanceNotFoundException {
        try {
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);

            this.service.createRouteDays(route2, 3);

            List<D> days = this.service.getAllRouteDays(route2.getId(), null, null);

            assertEquals(days.size(), 3);

        } finally {
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testUpdateRouteDay() throws UnUpdateableRouteException {
        try {
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            day1.setStartTime(0L);
            day1 = this.service.updateRouteDay(day1);

            assertEquals(day1.getStartTime(), 0L, 0.01);

        } finally {
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testDeleteRouteDay() throws InstanceNotFoundException, UnUpdateableRouteException {
        try {
            Boolean exc = false;
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            D day2 = this.insertValidRouteDay(route1, 0L);

            this.service.deleteRouteDay(day2.getDiaPK().getIdRoute(), day2.getDiaPK().getIdDay());

            List<D> days = this.service.getAllRouteDays(null, null);
            assertEquals(days.size(), 1);

            try {
                this.service.deleteRouteDay(0L, 0L);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }

            assertTrue(exc);

        } finally {
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetAllRouteDaysLongIntegerInteger() throws UnUpdateableRouteException {
        try {
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            D day2 = this.insertValidRouteDay(route1, 0L);

            List<D> days = this.service.getAllRouteDays(route1.getId(), null, null);

            assertEquals(days.size(), 2);

        } finally {
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetAllRouteDaysIntegerInteger() throws UnUpdateableRouteException {
        try {
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            D day2 = this.insertValidRouteDay(route1, 0L);

            List<D> days = this.service.getAllRouteDays(null, null);

            assertEquals(days.size(), 2);

        } finally {
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetRouteDaysByField() throws UnUpdateableRouteException, InputValidationException {
        try {
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            D day2 = this.insertValidRouteDay(route1, 0L);

            List<D> days = this.service.getRouteDaysByField("startTime", 0L, null, null);

            assertEquals(days.size(), 1);

        } finally {
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetRouteDayById() throws InstanceNotFoundException, UnUpdateableRouteException {
        try {
            Boolean exc = false;
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            D day2 = this.insertValidRouteDay(route1, 0L);

            D day = this.service.getRouteDayById(day1.getDiaPK().getIdRoute(), day1.getDiaPK().getIdDay());

            assertEquals(day.getDiaPK().getIdRoute(), day1.getDiaPK().getIdRoute());

            try {
                this.service.getRouteDayById(0L, 0L);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }

            assertTrue(exc);

        } finally {
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetRouteDaysByFields() throws UnUpdateableRouteException {
        try {
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            D day2 = this.insertValidRouteDay(route1, 0L);

            List<D> days = this.service.getRouteDaysByFields(day1.getDiaPK().getIdRoute(), "startTime", 0L, null, null);

            assertEquals(days.size(), 1);

        } finally {
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetAllStays() throws UnUpdateableRouteException {
        try {
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            D day2 = this.insertValidRouteDay(route1, 0L);
            S stay1 = this.insertValidStay(day1);
            S stay2 = this.insertValidStay(day1);

            List<S> stays = this.service.getAllStays(null, null);

            assertEquals(stays.size(), 2);

        } finally {
            this.stayDao.clearTable();
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetStaysByField() throws UnUpdateableRouteException {
        try {
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            D day2 = this.insertValidRouteDay(route1, 0L);
            S stay1 = this.insertValidStay(day1);
            S stay2 = this.insertValidStay(day1);

            List<S> stays = this.service.getStaysByField("travelMode", "WALKING", null, null);

            assertEquals(stays.size(), 2);

        } finally {
            this.stayDao.clearTable();
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetStaysByFields() throws UnUpdateableRouteException {
        try {
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            D day2 = this.insertValidRouteDay(route1, 0L);
            S stay1 = this.insertValidStay(day1);
            S stay2 = this.insertValidStay(day1);

            List<S> stays = this.service.getStaysByFields(stay1.getDay().getDiaPK().getIdRoute(),
                    stay1.getDay().getDiaPK().getIdDay(), "", "", null, null);

            assertEquals(stays.size(), 2);

            stays = this.service.getStaysByFields(stay1.getDay().getDiaPK().getIdRoute(), null, "", "", null, null);

            assertEquals(stays.size(), 2);

        } finally {
            this.stayDao.clearTable();
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetAllStaysInDay() throws UnUpdateableRouteException {
        try {
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            D day2 = this.insertValidRouteDay(route1, 0L);
            S stay1 = this.insertValidStay(day1);
            S stay2 = this.insertValidStay(day1);

            List<S> stays = this.service.getAllStaysInDay(stay1.getDay().getDiaPK().getIdRoute(),
                    stay1.getDay().getDiaPK().getIdDay());

            assertEquals(stays.size(), 2);

        } finally {
            this.stayDao.clearTable();
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetStayById() throws UnUpdateableRouteException, InstanceNotFoundException {
        try {
            Boolean exc = false;
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            D day2 = this.insertValidRouteDay(route1, 0L);
            S stay1 = this.insertValidStay(day1);
            S stay2 = this.insertValidStay(day1);

            S stay = this.service.getStayById(stay1.getId());

            assertEquals(stay.getId(), stay1.getId());

            try {
                this.service.getStayById(0L);
            } catch (InstanceNotFoundException e) {
                exc = true;
            }

            assertTrue(exc);
        } finally {
            this.stayDao.clearTable();
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testDeleteStay() throws UnUpdateableRouteException, InstanceNotFoundException {
        try {
            Boolean exc = false;
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            D day2 = this.insertValidRouteDay(route1, 0L);
            S stay1 = this.insertValidStay(day1);
            S stay2 = this.insertValidStay(day1);

            this.service.deleteStay(stay1.getId());
            List<S> stays = this.service.getAllStays(null, null);

            assertEquals(stays.size(), 1);

            try {
                this.service.deleteStay(0L);
                ;
            } catch (InstanceNotFoundException e) {
                exc = true;
            }

            assertTrue(exc);
        } finally {
            this.stayDao.clearTable();
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testUpdateStay() throws UnUpdateableRouteException {
        try {
            Boolean exc = false;
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            D day2 = this.insertValidRouteDay(route1, 0L);
            S stay1 = this.insertValidStay(day1);
            S stay2 = this.insertValidStay(day1);

            stay1.setTravelDistance(100L);
            stay1 = this.service.updateStay(stay1);

            assertEquals(stay1.getTravelDistance(), 100L, 0.01);

        } finally {
            this.stayDao.clearTable();
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testGetStayMaxOrderNum() throws UnUpdateableRouteException {
        try {
            Boolean exc = false;
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            D day2 = this.insertValidRouteDay(route1, 0L);
            S stay1 = this.insertValidStay(day1);
            S stay2 = this.insertValidStay(day1);

            Integer num = this.service.getStayMaxOrderNum(stay1.getDay().getDiaPK().getIdRoute(),
                    stay1.getDay().getDiaPK().getIdDay());

            assertEquals(num, 3, 0.01);

        } finally {
            this.stayDao.clearTable();
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

    @Transactional
    @Test
    public void testFixStaysOrdersAfterDelete() throws UnUpdateableRouteException, InstanceNotFoundException {
        try {
            Boolean exc = false;
            R route1 = this.insertValidRoute("city1", true);
            R route2 = this.insertValidRoute("city2", false);
            D day1 = this.insertValidRouteDay(route1, 10L);
            D day2 = this.insertValidRouteDay(route1, 0L);
            S stay1 = this.insertValidStay(day1);
            S stay2 = this.insertValidStay(day1);
            S stay3 = this.insertValidStay(day1);

            this.service.deleteStay(stay2.getId());
            this.service.fixStaysOrdersAfterDelete(route1.getId(), day1.getDiaPK().getIdDay());

            stay3 = this.service.getStayById(stay3.getId());
            R route = this.service.getRouteById(route1.getId());
            assertEquals(stay3.getOrder(), 2);

        } finally {
            this.stayDao.clearTable();
            this.routeDayDao.clearTable();
            this.routeDao.clearTable();
        }
    }

}
