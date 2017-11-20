package es.udc.rdopazo.tfg.app.model.core.stay;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;

public interface StayService<V extends Stay<?, ?>> {

    List<V> getAll();

    V getById(Long id);

    V add(V visita);

    V update(V visita);

    void delete(Long id);
}
