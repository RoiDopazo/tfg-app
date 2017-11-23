package es.udc.rdopazo.tfg.app.model.core.event.day.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.EventService;
import es.udc.rdopazo.tfg.app.model.core.event.day.EventDayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.dao.EventDayDao;

@Service
public class EventDayServiceImpl<E extends Event<ED>, ED extends EventDay<?>> implements EventDayService<ED> {

    @Autowired
    private EventDayDao<ED> dao;

    @Autowired
    private EventService<E> eventService;

    public ED add(Long idEvent, ED day) {
        E event = this.eventService.getById(idEvent);
        event.addDay(day);
        this.dao.add(day);
        return day;
    }

    public ED update(ED day) {
        // TODO Auto-generated method stub
        return null;
    }

    public void delete(Long idEvent, Long idDay) {
        // TODO Auto-generated method stub

    }

    public List<ED> getAll(Long idEvent, Integer index, Integer count) {
        return this.dao.getAll(idEvent, index, count);
    }

    public ED getById(Long idEvent, Long idDay) {
        // TODO Auto-generated method stub
        return null;
    }

}
