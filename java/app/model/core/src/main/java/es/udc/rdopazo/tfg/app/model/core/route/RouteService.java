package es.udc.rdopazo.tfg.app.model.core.route;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.UnUpdateableRouteException;

public interface RouteService<R extends Route<D, ?>, D extends RouteDay<?>, S extends Stay<D, ?, ?>> {

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.priv == false")
    List<R> getAllRoutes(Integer index, Integer count);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.priv == false")
    List<R> exploreRoutes(Long idUser, String city, String state, Long numDays, Long maxDistance, Long maxDuration,
            Integer index, Integer count);

    @PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.user.username == authentication.principal.username or returnObject.priv == false")
    R getRouteById(Long id) throws InstanceNotFoundException;

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.user.username == authentication.principal.username or filterObject.priv == false")
    List<R> getRoutesByField(String field, Object value, Integer index, Integer count);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.user.username == authentication.principal.username")
    List<R> getRoutesByFields(Long idUser, String filter, Object value, Integer index, Integer count);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    R addRoute(R route);

    @PreAuthorize("hasRole('ROLE_ADMIN') or #route.user.username == authentication.principal.username")
    R updateRoute(R route) throws UnUpdateableRouteException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or #route.user.username == authentication.principal.username")
    R updateRoutePriv(R route);

    @PreAuthorize("hasRole('ROLE_ADMIN') or @mySecurityService.hasRoutePermission(authentication, #id)")
    void deleteRoute(Long id) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or #route.user.username == authentication.principal.username")
    D addRouteDay(R route) throws UnUpdateableRouteException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or #route.user.username == authentication.principal.username")
    D addRouteDay(R route, Long startTime, String realTimeData) throws UnUpdateableRouteException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or #route.user.username == authentication.principal.username")
    List<D> createRouteDays(R route, Integer numDays) throws InstanceNotFoundException, UnUpdateableRouteException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or #day.route.user.username == authentication.principal.username")
    D updateRouteDay(D day);

    @PreAuthorize("hasRole('ROLE_ADMIN') or @mySecurityService.hasRouteDayPermission(authentication, #idRoute, #idDay)")
    void deleteRouteDay(Long idRoute, Long idDay) throws InstanceNotFoundException, UnUpdateableRouteException;

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.route.user.username == authentication.principal.username or filterObject.route.priv == false")
    List<D> getAllRouteDays(Long idRoute, Integer index, Integer count);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.route.user.username == authentication.principal.username or filterObject.route.priv == false")
    List<D> getAllRouteDays(Integer index, Integer count);

    @PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.route.user.username == authentication.principal.username or filterObject.route.priv == false")
    D getRouteDayById(Long idRoute, Long idDay) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or @mySecurityService.hasRoutePermission(authentication, #idRoute)")
    List<Long> getRouteDaysByRotueAndPlace(Long idRoute, String idFoursquare);

    @PostFilter("hasRole('ROLE_ADMIN') or returnObject.route.user.username == authentication.principal.username")
    List<D> getRouteDaysByField(String field, Object value, Integer index, Integer count);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.route.user.username == authentication.principal.username or filterObject.route.priv == false")
    List<D> getRouteDaysByFields(Long idRoute, String filter, Object value, Integer index, Integer count);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.day.route.user.username == authentication.principal.username or filterObject.day.route.priv == false")
    List<S> getAllStays(Integer index, Integer count);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.day.route.user.username == authentication.principal.username or filterObject.day.route.priv == false")
    List<S> getStaysByField(String field, String value, Integer index, Integer count);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.day.route.user.username == authentication.principal.username or filterObject.day.route.priv == false")
    List<S> getStaysByFields(Long idRoute, Long idDay, String filter, String value, Integer index, Integer count);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.day.route.user.username == authentication.principal.username or filterObject.day.route.priv == false")
    List<S> getStaysByRouteDayAndPlace(Long idRoute, Long idDay, Long idPlace);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.day.route.user.username == authentication.principal.username or filterObject.day.route.priv == false")
    List<S> getAllStaysInDay(Long idRoute, Long idDay);

    @PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.day.route.user.username == authentication.principal.username or filterObject.day.route.priv == false")
    S getStayById(Long id) throws InstanceNotFoundException;

    S addStay(S stay) throws UnUpdateableRouteException;

    S updateStay(S stay) throws UnUpdateableRouteException;

    void deleteStay(Long id) throws InstanceNotFoundException, UnUpdateableRouteException;

    Integer getStayMaxOrderNum(Long idRoute, Long idDay);

    void fixStaysOrdersAfterDelete(Long idRoute, Long idDay) throws UnUpdateableRouteException;
}
