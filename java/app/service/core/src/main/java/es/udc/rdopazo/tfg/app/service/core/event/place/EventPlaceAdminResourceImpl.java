package es.udc.rdopazo.tfg.app.service.core.event.place;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.place.EventPlaceService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.service.core.event.place.converter.EventPlacePersistEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.event.place.updater.EventPlaceEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.InputValidator;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.event.place.EventPlaceAdminResource;
import es.udc.rdopazo.tfg.service.api.event.place.dto.EventPlacePersistDto;

@Service
public class EventPlaceAdminResourceImpl<EP extends EventPlace<?>> implements EventPlaceAdminResource {

    @Autowired
    private EventPlaceService<EP> service;

    @Autowired
    private EventPlacePersistEntityDtoConverter<EP, EventPlacePersistDto> converter;

    @Autowired
    private EventPlaceEntityDtoUpdater<EP> updater;

    public List<EventPlacePersistDto> getAll(String event, String day, String filter, String value, String index,
            String count) throws InputValidationException {
        Integer indexInt = InputValidator.validateIntegerNull("index", index);
        Integer countInt = InputValidator.validateIntegerNull("count", count);
        Long idEvent = InputValidator.validateLongNull("idEvent", event);
        Long idDay = InputValidator.validateLongNull("idDay", day);

        List<EventPlacePersistDto> result = this.converter
                .toDtoList(this.service.getByFields(idEvent, idDay, filter, value, indexInt, countInt));
        return result;
    }

    public EventPlacePersistDto getById(String id) throws InputValidationException, InstanceNotFoundException {
        Long idEventPlace = InputValidator.validateLongNull("idEventPlace", id);
        return this.converter.toDto(this.service.getById(idEventPlace));
    }

    public EventPlacePersistDto create(String idEvent, String idDay, EventPlacePersistDto eventPlacePersistDto)
            throws InstanceNotFoundException, InputValidationException {
        Long idEventLong = InputValidator.validateLongNull("idEventPlace", idEvent);
        Long idDayLong = InputValidator.validateLongNull("idDay", idDay);
        eventPlacePersistDto.setId(null);
        EP eventPlace = this.converter.toEntity(eventPlacePersistDto);
        eventPlace = this.service.add(idEventLong, idDayLong, eventPlace);
        return this.converter.toDto(eventPlace);
    }

    public EventPlacePersistDto update(String id, EventPlacePersistDto eventPlacePersistDto)
            throws InstanceNotFoundException, InputValidationException {
        Long idEventPlace = InputValidator.validateLongNull("idEventPlace", id);
        EP eventPlace = this.service.getById(idEventPlace);
        eventPlace = this.updater.updatePersist(eventPlacePersistDto, eventPlace);
        return this.converter.toDto(this.service.update(eventPlace));
    }

    public void delete(String id) throws InputValidationException, InstanceNotFoundException {
        Long idEventPlace = InputValidator.validateLongNull("idEventPlace", id);
        this.service.delete(idEventPlace);

    }

}
