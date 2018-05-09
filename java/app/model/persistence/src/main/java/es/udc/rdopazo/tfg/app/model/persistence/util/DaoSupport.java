package es.udc.rdopazo.tfg.app.model.persistence.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract interface DaoSupport<PK extends Serializable, E extends Entity<PK>> {

    void add(E entidad);

    void update(E entidad);

    void remove(E entidad);

    List<E> getAll();

    List<E> getAll(Integer index, Integer count);

    E getById(PK id);

    List<E> getListByField(String fieldName, Object value);

    List<E> getListByField(String fieldName, Object value, OrderingType orderingType, String orderingField);

    List<E> getListByField(String fieldName, Object value, Integer index, Integer count);

    List<E> getListByField(String fieldName, Object value, OrderingType orderingType, String orderingField,
            Integer index, Integer count);

    List<E> getListByFields(Map<String, Object> fields);

    List<E> getListByFields(Map<String, Object> fields, Integer index, Integer count);

    void clearTable();
}
