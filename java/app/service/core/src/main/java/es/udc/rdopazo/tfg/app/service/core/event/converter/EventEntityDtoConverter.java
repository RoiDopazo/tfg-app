package es.udc.rdopazo.tfg.app.service.core.event.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.core.event.day.EventDayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.JpaEvent;
import es.udc.rdopazo.tfg.app.service.core.event.day.converter.EventDayEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.event.day.dto.EventDayDto;
import es.udc.rdopazo.tfg.service.api.event.dto.EventDto;

@Component
public class EventEntityDtoConverter<D extends EventDto, E extends Event<ED>, ED extends EventDay<EP>, EP extends EventPlace<ED>>
        extends DefaultEntityDtoConverterSupport<D, E> {

    @Autowired
    private EventDayEntityDtoConverter<EventDayDto, ED, EP> eventDayConverter;

    @Autowired
    private EventDayService<ED> eventDayService;

    @Override
    protected Class<?> getEntityClass() {
        return JpaEvent.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return EventDto.class;
    }

    @Override
    public D toDto(E entity) {
        @SuppressWarnings("unchecked")
        D dto = (D) new EventDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCity(entity.getCity());
        dto.setDescription(entity.getDescription());
        dto.setEndDate(entity.getEndDate());
        dto.setStartDate(entity.getStartDate());
        dto.setDays(this.eventDayConverter.toDtoList(this.eventDayService.getAll(entity.getId(), null, null)));

        return dto;
    }
}
