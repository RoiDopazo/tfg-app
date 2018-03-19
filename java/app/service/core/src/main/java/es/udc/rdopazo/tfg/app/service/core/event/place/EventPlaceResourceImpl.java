package es.udc.rdopazo.tfg.app.service.core.event.place;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.day.EventDayService;
import es.udc.rdopazo.tfg.app.model.core.event.place.EventPlaceService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.service.core.event.place.converter.EventPlaceEntityDtoConverter;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.event.place.EventPlaceResource;
import es.udc.rdopazo.tfg.service.api.event.place.dto.EventPlaceDto;

@Service
public class EventPlaceResourceImpl<E extends Event<ED>, ED extends EventDay<E, EP>, EP extends EventPlace<ED>>
        implements EventPlaceResource {

    @Autowired
    private EventPlaceService<EP> service;

    @Autowired
    private EventPlaceEntityDtoConverter<EventPlaceDto, EP> converter;

    @Autowired
    private EventDayService<ED> dayService;

    public List<EventPlaceDto> getAll(String index, String count) {
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

    public EventPlaceDto getById(String id) throws InstanceNotFoundException {
        Long idLong = null;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {

        }
        return this.converter.toDto(this.service.getById(idLong));
    }

}
