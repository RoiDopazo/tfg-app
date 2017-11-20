package es.udc.rdopazo.tfg.app.model.core.route;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;

public interface RouteService<R extends Route<?>> {

    List<R> getAll(Integer index, Integer count);

    R getById(Long id);

    R add(R ruta);

    R update(R ruta);

    void delete(Long id);
}
