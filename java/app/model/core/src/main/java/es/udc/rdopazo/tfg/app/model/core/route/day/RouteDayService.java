package es.udc.rdopazo.tfg.app.model.core.route.day;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public interface RouteDayService<R extends Route<D, ?>, D extends RouteDay<?>> {

    @PreAuthorize("hasRole('ROLE_ADMIN') or #route.user.username == authentication.principal.username")
    D add(R route);

    @PreAuthorize("hasRole('ROLE_ADMIN') or #route.user.username == authentication.principal.username")
    D add(R route, Long startTime, String realTimeData);

    @PreAuthorize("hasRole('ROLE_ADMIN') or #route.user.username == authentication.principal.username")
    List<D> createDays(R route, Integer numDays) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or #day.route.user.username == authentication.principal.username")
    D update(D day);

    @PreAuthorize("hasRole('ROLE_ADMIN') or @mySecurityService.hasRouteDayPermission(authentication, #idRoute, #idDay)")
    void delete(Long idRoute, Long idDay) throws InstanceNotFoundException;

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.route.user.username == authentication.principal.username or filterObject.route.priv == false")
    List<D> getAll(Long idRoute, Integer index, Integer count);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.route.user.username == authentication.principal.username or filterObject.route.priv == false")
    List<D> getAll(Integer index, Integer count);

    @PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.route.user.username == authentication.principal.username or filterObject.route.priv == false")
    D getById(Long idRoute, Long idDay) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or @mySecurityService.hasRoutePermission(authentication, #idRoute)")
    List<Long> getListDaysByRotueAndPlace(Long idRoute, String idFoursquare);

    @PostFilter("hasRole('ROLE_ADMIN') or returnObject.route.user.username == authentication.principal.username")
    List<D> getByField(String field, String value, Integer index, Integer count) throws InputValidationException;

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.route.user.username == authentication.principal.username or filterObject.route.priv == false")
    List<D> getByFields(Long idRoute, String filter, Object value, Integer index, Integer count);

}