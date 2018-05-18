package es.udc.rdopazo.tfg.app.model.persistence.api.route.day.dao;

import java.util.List;
import java.util.Map;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.util.OrderingType;

public interface RouteDayDao<D extends RouteDay<?>> {

    void add(D entidad);

    void update(D entidad);

    void remove(D entidad);

    List<D> getAll(Integer index, Integer count);

    List<D> getAll(Long idRoute);

    List<D> getAll(Long idRoute, Integer index, Integer count);

    List<D> getAll(Long idRoute, Integer index, Integer count, OrderingType orderingType);

    D getById(Long idRoute, Long idDay);

    List<D> getListByField(String fieldName, Object value);

    List<D> getListByField(String fieldName, Object value, OrderingType orderingType, String orderingField);

    List<D> getListByField(String fieldName, Object value, Integer index, Integer count);

    List<D> getListByField(String fieldName, Object value, OrderingType orderingType, Integer index, Integer count);

    List<D> getListByFields(Map<String, Object> fields, Integer index, Integer count);

    void clearTable();
}
