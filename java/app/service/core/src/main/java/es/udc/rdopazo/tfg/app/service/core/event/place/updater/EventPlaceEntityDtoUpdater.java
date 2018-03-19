package es.udc.rdopazo.tfg.app.service.core.event.place.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.service.api.event.place.dto.EventPlacePersistDto;

@Component
public class EventPlaceEntityDtoUpdater<EP extends EventPlace<?>> {

    public EP updatePersist(EventPlacePersistDto dto, EP entity) {
        entity.setAddress(dto.getAddress());
        entity.setDescription(dto.getDescription());
        entity.setEndHour(dto.getEndHour());
        entity.setLat(dto.getLat());
        entity.setLng(dto.getLng());
        entity.setName(dto.getName());
        entity.setStartHour(dto.getStartHour());
        return entity;
    }
}
