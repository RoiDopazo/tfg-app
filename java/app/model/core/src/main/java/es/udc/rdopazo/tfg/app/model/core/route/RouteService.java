package es.udc.rdopazo.tfg.app.model.core.route;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;

public interface RouteService<R extends Route<?, ?>> {

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.user.username == authentication.principal.username or filterObject.priv == false")
    List<R> getAll(Integer index, Integer count);

    @PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.user.username == authentication.principal.username or filterObject.priv == false")
    R getById(Long id);

    R add(R ruta);

    @PreAuthorize("hasRole('ROLE_ADMIN') or #ruta.user.username == authentication.principal.username or filterObject.priv == false")
    R update(R ruta);

    void delete(Long id);
}
