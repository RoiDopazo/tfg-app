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

    public List<E> getListByFields(Map<String, Object> fields);

}
