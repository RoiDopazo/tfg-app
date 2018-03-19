package es.udc.rdopazo.tfg.app.service.core.event.day.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.service.api.event.day.dto.EventDayPersistDto;

@Component
public class EventDayEntityDtoUpdater<ED extends EventDay<?, ?>> {

    public ED updatePersist(EventDayPersistDto dto, ED entity) {
        entity.setDate(dto.getDate());
        return entity;
    }

}
