package es.udc.rdopazo.tfg.app.service.core.event.day;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.day.EventDayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.service.core.event.day.converter.EventDayEntityDtoConverter;
import es.udc.rdopazo.tfg.service.api.event.day.EventDayResource;
import es.udc.rdopazo.tfg.service.api.event.day.dto.EventDayDto;

@Service
public class EventDayResourceImpl<E extends Event<ED>, ED extends EventDay<EP>, EP extends EventPlace>
        implements EventDayResource {

    @Autowired
    private EventDayService<ED> service;

    @Autowired
    private EventDayEntityDtoConverter<EventDayDto, ED, EP> converter;

    @Override
    public List<EventDayDto> getAll(String idEvent, String index, String count) {
        Integer indexInt = null;
        Integer countInt = null;
        Long idEventLong = null;
        try {
            idEventLong = Long.parseLong(idEvent);
        } catch (NumberFormatException e) {

        }

        try {
            indexInt = Integer.parseInt(index);
        } catch (NumberFormatException e) {

        }
        try {
            countInt = Integer.parseInt(count);
        } catch (NumberFormatException e) {

        }

        return this.converter.toDtoList(this.service.getAll(idEventLong, indexInt, countInt));
    }

    @Override
    public EventDayDto update(String idEvent, String idDay, EventDayDto eventDayDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EventDayDto create(String idEvent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(String idEvent) {
        // TODO Auto-generated method stub

    }

}
