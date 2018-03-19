package es.udc.rdopazo.tfg.app.service.core.event.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.service.api.event.dto.EventPersistDto;

@Component
public class EventEntityDtoUpdater<E extends Event<?>> {

    public E updatePersist(EventPersistDto dto, E entity) {
        entity.setCity(dto.getCity());
        entity.setDescription(dto.getDescription());
        entity.setEndDate(dto.getEndDate());
        entity.setName(dto.getName());
        entity.setStartDate(dto.getStartDate());

        return entity;
    }

}
