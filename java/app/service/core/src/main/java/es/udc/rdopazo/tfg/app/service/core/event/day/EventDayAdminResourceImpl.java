package es.udc.rdopazo.tfg.app.service.core.event.day;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.EventService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.service.core.event.day.converter.EventDayPersistEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.event.day.updater.EventDayEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.InputValidator;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.event.day.EventDayAdminResource;
import es.udc.rdopazo.tfg.service.api.event.day.dto.EventDayPersistDto;

@Service
public class EventDayAdminResourceImpl<E extends Event<ED>, ED extends EventDay<E, EP>, EP extends EventPlace<ED>>
        implements EventDayAdminResource {

    @Autowired
    private EventService<E, ED, EP> service;

    @Autowired
    private EventDayPersistEntityDtoConverter<EventDayPersistDto, ED> converter;

    @Autowired
    private EventDayEntityDtoUpdater<ED> updater;

    public List<EventDayPersistDto> getAll(String event, String day, String filter, String value, String index,
            String count) throws InputValidationException, InstanceNotFoundException {
        Integer indexInt = InputValidator.validateIntegerNull("index", index);
        Integer countInt = InputValidator.validateIntegerNull("count", count);
        Long idEvent = InputValidator.validateLongNull("idEvent", event);
        Long idDay = InputValidator.validateLongNull("idDay", day);

        List<EventDayPersistDto> result = this.converter
                .toDtoList(this.service.getEventDaysByFields(idEvent, idDay, filter, value, indexInt, countInt));
        return result;
    }

    public EventDayPersistDto getById(String idEvent, String idDay)
            throws InputValidationException, InstanceNotFoundException {
        Long idEventLong = InputValidator.validateLongNull("idEvent", idEvent);
        Long idDayLong = InputValidator.validateLongNull("idEventDay", idDay);
        return this.converter.toDto(this.service.getEventDayById(idEventLong, idDayLong));
    }

    public EventDayPersistDto update(String idEvent, String idDay, EventDayPersistDto eventDayPersistDto)
            throws InputValidationException, InstanceNotFoundException {
        Long idEventLong = InputValidator.validateLongNull("idEvent", idEvent);
        Long idDayLong = InputValidator.validateLongNull("idEventDay", idDay);
        ED eventDay = this.service.getEventDayById(idEventLong, idDayLong);
        eventDay = this.updater.updatePersist(eventDayPersistDto, eventDay);
        return this.converter.toDto(this.service.updateEventDay(eventDay));
    }

    public EventDayPersistDto create(String idEvent, EventDayPersistDto eventDayPersistDto)
            throws InstanceNotFoundException, InputValidationException {
        eventDayPersistDto.setIdDay(null);
        eventDayPersistDto.setIdEvent(null);
        Long idEventLong = InputValidator.validateLongNull("idEvent", idEvent);
        ED eventDay = this.converter.toEntity(eventDayPersistDto);
        return this.converter.toDto(this.service.addEventDay(idEventLong, eventDay));
    }

    public void delete(String idEvent, String idDay) throws InputValidationException, InstanceNotFoundException {
        Long idEventLong = InputValidator.validateLongNull("idEvent", idEvent);
        Long idDayLong = InputValidator.validateLongNull("idEventDay", idDay);
        this.service.deleteEventDay(idEventLong, idDayLong);
    }

}
