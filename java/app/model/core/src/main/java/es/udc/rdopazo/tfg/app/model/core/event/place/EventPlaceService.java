package es.udc.rdopazo.tfg.app.model.core.event.place;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public interface EventPlaceService<EP extends EventPlace> {

    List<EP> getAll(Integer index, Integer count);

    EP getById(Long id) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MODERATOR')")
    EP add(Long idEvent, Long idDay, EP eventPlace) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MODERATOR')")
    EP update(EP eventPlace);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('MODERATOR')")
    void delete(Long id) throws InstanceNotFoundException;

    List<EP> getByFields(Long idEvent, Long idDay, String filter, String value, Integer index, Integer count);
}
