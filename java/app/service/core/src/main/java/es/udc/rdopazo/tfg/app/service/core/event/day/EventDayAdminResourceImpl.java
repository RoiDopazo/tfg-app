package es.udc.rdopazo.tfg.app.service.core.event.day;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.day.EventDayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.service.core.event.day.converter.EventDayPersistEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.event.day.updater.EventDayEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.InputValidator;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.event.day.EventDayAdminResource;
import es.udc.rdopazo.tfg.service.api.event.day.dto.EventDayPersistDto;

@Service
public class EventDayAdminResourceImpl<ED extends EventDay<?, ?>> implements EventDayAdminResource {

    @Autowired
    private EventDayService<ED> service;

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
                .toDtoList(this.service.getByFields(idEvent, idDay, filter, value, indexInt, countInt));
        return result;
    }

    public EventDayPersistDto getById(String idEvent, String idDay, String index, String count)
            throws InputValidationException, InstanceNotFoundException {
        Long idEventLong = InputValidator.validateLongNull("idEvent", idEvent);
        Long idDayLong = InputValidator.validateLongNull("idEventDay", idDay);
        return this.converter.toDto(this.service.getById(idEventLong, idDayLong));
    }

    public EventDayPersistDto update(String idEvent, String idDay, EventDayPersistDto eventDayPersistDto)
            throws InputValidationException, InstanceNotFoundException {
        Long idEventLong = InputValidator.validateLongNull("idEvent", idEvent);
        Long idDayLong = InputValidator.validateLongNull("idEventDay", idDay);
        ED eventDay = this.service.getById(idEventLong, idDayLong);
        eventDay = this.updater.updatePersist(eventDayPersistDto, eventDay);
        return this.converter.toDto(this.service.update(eventDay));
    }

    public EventDayPersistDto create(String idEvent, EventDayPersistDto eventDayPersistDto)
            throws InstanceNotFoundException, InputValidationException {
        eventDayPersistDto.setIdDay(null);
        eventDayPersistDto.setIdEvent(null);
        Long idEventLong = InputValidator.validateLongNull("idEvent", idEvent);
        ED eventDay = this.converter.toEntity(eventDayPersistDto);
        return this.converter.toDto(this.service.add(idEventLong, eventDay));
    }

    public void delete(String idEvent, String idDay) throws InputValidationException, InstanceNotFoundException {
        Long idEventLong = InputValidator.validateLongNull("idEvent", idEvent);
        Long idDayLong = InputValidator.validateLongNull("idEventDay", idDay);
        this.service.delete(idEventLong, idDayLong);
    }

}
