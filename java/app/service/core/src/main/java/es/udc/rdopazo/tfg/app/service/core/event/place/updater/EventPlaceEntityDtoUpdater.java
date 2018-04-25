package es.udc.rdopazo.tfg.app.service.core.event.place.updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.core.event.EventService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.event.place.dto.EventPlacePersistDto;

@Component
public class EventPlaceEntityDtoUpdater<E extends Event<ED>, ED extends EventDay<E, EP>, EP extends EventPlace<ED>> {

    @Autowired
    private EventService<E, ED, EP> service;

    public EP updatePersist(EventPlacePersistDto dto, EP entity) throws InstanceNotFoundException {
        entity.setAddress(dto.getAddress());
        entity.setDescription(dto.getDescription());
        entity.setEndHour(dto.getEndHour());
        entity.setLat(dto.getLat());
        entity.setLng(dto.getLng());
        entity.setName(dto.getName());
        entity.setStartHour(dto.getStartHour());
        entity.setDay(this.service.getEventDayById(dto.getIdEvent(), dto.getIdDay()));
        return entity;
    }
}
