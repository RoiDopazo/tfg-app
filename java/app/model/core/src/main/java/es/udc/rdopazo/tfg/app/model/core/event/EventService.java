package es.udc.rdopazo.tfg.app.model.core.event;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public interface EventService<E extends Event<?>> {

    List<E> getAll(Integer index, Integer count);

    List<E> getByField(String field, Object value, Integer index, Integer count);

    E getById(Long id) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MODERATOR')")
    E add(E event);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MODERATOR')")
    E update(E event);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MODERATOR')")
    void delete(Long id) throws InstanceNotFoundException;
}
