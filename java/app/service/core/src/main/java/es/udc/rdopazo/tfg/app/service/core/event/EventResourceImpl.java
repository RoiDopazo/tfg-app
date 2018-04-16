package es.udc.rdopazo.tfg.app.service.core.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.EventService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.service.core.event.converter.EventEntityDtoConverter;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.event.EventResource;
import es.udc.rdopazo.tfg.service.api.event.dto.EventDto;

@Service
public class EventResourceImpl<E extends Event<ED>, ED extends EventDay<E, EP>, EP extends EventPlace<ED>>
        implements EventResource {

    @Autowired
    private EventService<E> service;

    @Autowired
    private EventEntityDtoConverter<EventDto, E> converter;

    public List<EventDto> getAll(String index, String count) {
        Integer indexInt = null;
        Integer countInt = null;
        try {
            indexInt = Integer.parseInt(index);
        } catch (NumberFormatException e) {

        }
        try {
            countInt = Integer.parseInt(count);
        } catch (NumberFormatException e) {

        }

        return this.converter.toDtoList(this.service.getAll(indexInt, countInt));
    }

    public EventDto getById(String id) throws InstanceNotFoundException {
        Long idLong = null;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {

        }
        return this.converter.toDto(this.service.getById(idLong));
    }

}
