package es.udc.rdopazo.tfg.app.model.core.route;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public interface RouteService<R extends Route<?, ?>> {

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.priv == false")
    List<R> getAll(Integer index, Integer count);

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.priv == false")
    List<R> explore(Long idUser, String city, String state, Long numDays, Long maxDistance, Long maxDuration,
            Integer index, Integer count);

    @PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.user.username == authentication.principal.username or returnObject.priv == false")
    R getById(Long id) throws InstanceNotFoundException;

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.user.username == authentication.principal.username or filterObject.priv == false")
    List<R> getByField(String field, String value, Integer index, Integer count) throws InputValidationException;

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.user.username == authentication.principal.username")
    List<R> getByFields(Long idUser, String filter, Object value, Integer index, Integer count);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    R add(R ruta);

    @PreAuthorize("hasRole('ROLE_ADMIN') or #ruta.user.username == authentication.principal.username")
    R update(R ruta);

    @PreAuthorize("hasRole('ROLE_ADMIN') or @mySecurityService.hasRoutePermission(authentication, #id)")
    void delete(Long id) throws InstanceNotFoundException;
}
