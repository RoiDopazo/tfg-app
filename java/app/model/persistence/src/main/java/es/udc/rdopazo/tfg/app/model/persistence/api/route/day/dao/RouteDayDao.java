package es.udc.rdopazo.tfg.app.model.persistence.api.route.day.dao;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;

public interface RouteDayDao<D extends RouteDay<?>> {

    void add(D entidad);

    void update(D entidad);

    void remove(D entidad);

    List<D> getAll(Long idRoute);

    List<D> getAll(Long idRoute, Integer index, Integer count);

    D getById(Long idRoute, Long idDay);

    List<D> getListByField(String fieldName, Object value);
}
