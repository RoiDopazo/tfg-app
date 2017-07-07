package es.udc.rdopazo.tfg.app.model.persistence.util;

import java.io.Serializable;
import java.util.List;

public abstract interface DaoSupport<PK extends Serializable, E extends Entidad<PK>> {

    void add(E entidad);

    void update(E entidad);

    void remove(E entidad);

    List<E> getAll();

    E getById(PK id);

    List<E> getListByField(String fieldName, Object value);

}
