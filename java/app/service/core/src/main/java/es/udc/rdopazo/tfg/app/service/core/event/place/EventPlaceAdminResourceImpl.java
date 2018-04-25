package es.udc.rdopazo.tfg.app.service.core.event.place;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.EventService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.service.core.event.place.converter.EventPlacePersistEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.event.place.updater.EventPlaceEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.InputValidator;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.event.place.EventPlaceAdminResource;
import es.udc.rdopazo.tfg.service.api.event.place.dto.EventPlacePersistDto;

@Service
public class EventPlaceAdminResourceImpl<E extends Event<ED>, ED extends EventDay<E, EP>, EP extends EventPlace<ED>>
        implements EventPlaceAdminResource {

    @Autowired
    private EventService<E, ED, EP> service;

    @Autowired
    private EventPlacePersistEntityDtoConverter<EP, EventPlacePersistDto> converter;

    @Autowired
    private EventPlaceEntityDtoUpdater<E, ED, EP> updater;

    public List<EventPlacePersistDto> getAll(String event, String day, String filter, String value, String index,
            String count) throws InputValidationException {
        Integer indexInt = InputValidator.validateIntegerNull("index", index);
        Integer countInt = InputValidator.validateIntegerNull("count", count);
        Long idEvent = InputValidator.validateLongNull("idEvent", event);
        Long idDay = InputValidator.validateLongNull("idDay", day);

        List<EventPlacePersistDto> result = this.converter
                .toDtoList(this.service.getEventPlacesByFields(idEvent, idDay, filter, value, indexInt, countInt));
        return result;
    }

    public EventPlacePersistDto getById(String id) throws InputValidationException, InstanceNotFoundException {
        Long idEventPlace = InputValidator.validateLongNull("idEventPlace", id);
        return this.converter.toDto(this.service.getEventPlaceById(idEventPlace));
    }

    public EventPlacePersistDto create(EventPlacePersistDto eventPlacePersistDto)
            throws InstanceNotFoundException, InputValidationException {
        eventPlacePersistDto.setId(null);
        EP eventPlace = this.converter.toEntity(eventPlacePersistDto);
        eventPlace = this.service.addEventPlace(eventPlacePersistDto.getIdEvent(), eventPlacePersistDto.getIdDay(),
                eventPlace);
        return this.converter.toDto(eventPlace);
    }

    public EventPlacePersistDto update(String id, EventPlacePersistDto eventPlacePersistDto)
            throws InstanceNotFoundException, InputValidationException {
        Long idEventPlace = InputValidator.validateLongNull("idEventPlace", id);
        EP eventPlace = this.service.getEventPlaceById(idEventPlace);
        eventPlace = this.updater.updatePersist(eventPlacePersistDto, eventPlace);
        return this.converter.toDto(this.service.updateEventPlace(eventPlace));
    }

    public void delete(String id) throws InputValidationException, InstanceNotFoundException {
        Long idEventPlace = InputValidator.validateLongNull("idEventPlace", id);
        this.service.deleteEventPlace(idEventPlace);

    }

}
