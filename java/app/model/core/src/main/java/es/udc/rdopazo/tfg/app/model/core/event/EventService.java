package es.udc.rdopazo.tfg.app.model.core.event;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public interface EventService<E extends Event<?>> {

    List<E> getAll(Integer index, Integer count);

    List<E> getByField(String field, Object value, Integer index, Integer count);

    E getById(Long id) throws InstanceNotFoundException;

    E add(E event);

    E update(E event);

    void delete(Long id) throws InstanceNotFoundException;
}
