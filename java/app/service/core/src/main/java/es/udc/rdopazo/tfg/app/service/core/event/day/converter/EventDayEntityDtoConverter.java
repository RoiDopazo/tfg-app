package es.udc.rdopazo.tfg.app.service.core.event.day.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day.JpaEventDay;
import es.udc.rdopazo.tfg.app.service.core.event.converter.EventEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.util.ModelMapperSupport;
import es.udc.rdopazo.tfg.service.api.event.day.dto.EventDayDto;
import es.udc.rdopazo.tfg.service.api.event.dto.EventDto;
import es.udc.rdopazo.tfg.service.api.event.place.dto.EventPlaceDto;

@Component
public class EventDayEntityDtoConverter<D extends EventDayDto, E extends Event<ED>, ED extends EventDay<E, EP>, EP extends EventPlace<ED>> {

    @Autowired
    ModelMapperSupport modelMapper;

    @Autowired
    private EventEntityDtoConverter<EventDto, E> eventConverter;

    protected Class<?> getEntityClass() {
        return JpaEventDay.class;
    }

    protected Class<?> getDtoClass() {
        return EventDayDto.class;
    }

    public D toDto(ED entity) {
        List<EventPlaceDto> eventPlacesList = new ArrayList<EventPlaceDto>();
        @SuppressWarnings("unchecked")
        D dto = (D) new EventDayDto();
        dto.setIdEvent(entity.getDayPK().getIdEvent());
        dto.setIdDay(entity.getDayPK().getIdDay());
        dto.setDate(entity.getDate());
        dto.setNumEvPlaces(entity.getNumEvPlaces());
        dto.setEvent(this.eventConverter.toDto(entity.getEvent()));
        if (entity.getEventPlaces() != null) {
            for (EP eventPlace : entity.getEventPlaces()) {
                EventPlaceDto eventPlaceDto = this.modelMapper.getModelMapper().map(eventPlace, EventPlaceDto.class);
                eventPlacesList.add(eventPlaceDto);
            }
            dto.setEventPlaces(eventPlacesList);
        }

        return dto;
    }

    public List<D> toDtoList(List<ED> entities) {
        List<D> dtoList = new ArrayList<D>();
        for (ED entity : entities) {
            dtoList.add(this.toDto(entity));
        }
        return dtoList;
    }

    public ModelMapperSupport getModelMapperSupport() {
        return this.modelMapper;
    }
}
