package es.udc.rdopazo.tfg.app.service.core.event.place.updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.core.event.day.EventDayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.event.place.dto.EventPlacePersistDto;

@Component
public class EventPlaceEntityDtoUpdater<EP extends EventPlace<ED>, ED extends EventDay<?, ?>> {

    @Autowired
    private EventDayService<ED> eventDayService;

    public EP updatePersist(EventPlacePersistDto dto, EP entity) throws InstanceNotFoundException {
        entity.setAddress(dto.getAddress());
        entity.setDescription(dto.getDescription());
        entity.setEndHour(dto.getEndHour());
        entity.setLat(dto.getLat());
        entity.setLng(dto.getLng());
        entity.setName(dto.getName());
        entity.setStartHour(dto.getStartHour());
        entity.setDay(this.eventDayService.getById(dto.getIdEvent(), dto.getIdDay()));
        return entity;
    }
}
