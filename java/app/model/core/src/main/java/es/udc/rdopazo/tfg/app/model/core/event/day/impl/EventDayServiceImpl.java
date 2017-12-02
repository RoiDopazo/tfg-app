package es.udc.rdopazo.tfg.app.model.core.event.day.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.EventService;
import es.udc.rdopazo.tfg.app.model.core.event.day.EventDayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.dao.EventDayDao;

@Service
public class EventDayServiceImpl<E extends Event<ED>, ED extends EventDay<?, ?>> implements EventDayService<ED> {

    @Autowired
    private EventDayDao<ED> dao;

    @Autowired
    private EventService<E> eventService;

    @Transactional
    public ED add(Long idEvent, ED day) {
        E event = this.eventService.getById(idEvent);
        day.setNumEvPlaces(0);
        event.addDay(day);
        this.dao.add(day);
        return day;
    }

    @Transactional
    public ED update(ED day) {
        this.dao.update(day);
        return day;
    }

    @Transactional
    public void delete(Long idEvent, Long idDay) {
        this.dao.remove(this.dao.getById(idEvent, idDay));
    }

    public List<ED> getAll(Long idEvent, Integer index, Integer count) {
        return this.dao.getAll(idEvent, index, count);
    }

    public ED getById(Long idEvent, Long idDay) {
        return this.dao.getById(idEvent, idDay);
    }

    public List<ED> getAll(Integer index, Integer count) {
        return this.dao.getAll(index, count);
    }

    public List<ED> getAllByDateRange(Date start_date, Date end_date, String type, Integer index, Integer count) {
        if (type.equals("BETWEEN")) {
            if ((start_date != null) && (end_date != null)) {
                return this.dao.getListByDateInBetween(start_date, end_date, index, count);
            }
        } else if (type.equals("OVER")) {
            if (end_date != null) {
                return this.dao.getListByDateInBetween(null, end_date, index, count);
            }
        }
        return null;
    }

}
