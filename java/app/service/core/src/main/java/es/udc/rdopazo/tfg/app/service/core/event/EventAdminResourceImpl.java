package es.udc.rdopazo.tfg.app.service.core.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.EventService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.service.core.event.converter.EventPersistEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.event.updater.EventEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.InputValidator;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.event.EventAdminResource;
import es.udc.rdopazo.tfg.service.api.event.dto.EventPersistDto;

@Service
public class EventAdminResourceImpl<E extends Event<?>> implements EventAdminResource {

    @Autowired
    private EventService<E> service;

    @Autowired
    private EventEntityDtoUpdater<E> updater;

    @Autowired
    private EventPersistEntityDtoConverter<E, EventPersistDto> converter;

    public List<EventPersistDto> getAll(String filter, String value, String index, String count)
            throws InputValidationException {
        Integer indexInt = InputValidator.validateIntegerNull("index", index);
        Integer countInt = InputValidator.validateIntegerNull("count", count);

        List<EventPersistDto> result = null;
        if (!(filter.equals("null")) && !(value.equals("null"))) {
            result = this.converter.toDtoList(this.service.getByField(filter, value, indexInt, countInt));
        } else {
            result = this.converter.toDtoList(this.service.getAll(indexInt, countInt));
        }
        return result;
    }

    public EventPersistDto getById(String id) throws InputValidationException, InstanceNotFoundException {
        Long idEvent = InputValidator.validateLongNull("idEvent", id);
        return this.converter.toDto(this.service.getById(idEvent));
    }

    public EventPersistDto create(EventPersistDto eventPersistDto) {
        eventPersistDto.setId(null);
        E event = this.converter.toEntity(eventPersistDto);
        event = this.service.add(event);
        return this.converter.toDto(event);
    }

    public EventPersistDto update(String id, EventPersistDto eventPersistDto)
            throws InputValidationException, InstanceNotFoundException {
        Long idEvent = InputValidator.validateLongNull("idEvent", id);
        E event = this.service.getById(idEvent);
        event = this.updater.updatePersist(eventPersistDto, event);
        return this.converter.toDto(this.service.update(event));
    }

    public void delete(String id) throws InputValidationException, InstanceNotFoundException {
        Long idEvent = InputValidator.validateLongNull("idEvent", id);
        this.service.delete(idEvent);

    }

}
