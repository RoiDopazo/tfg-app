package es.udc.rdopazo.tfg.app.model.core.event;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;

public interface EventService<E extends Event<?>> {

    List<E> getAll(Integer index, Integer count);

    E getById(Long id);

    E add(E event);

    E update(E event);

    void delete(Long id);
}
