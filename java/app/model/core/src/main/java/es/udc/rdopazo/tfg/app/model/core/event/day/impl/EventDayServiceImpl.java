package es.udc.rdopazo.tfg.app.model.core.event.day.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.EventService;
import es.udc.rdopazo.tfg.app.model.core.event.day.EventDayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.dao.EventDayDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day.id.EventDayPK;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

@Service
public class EventDayServiceImpl<E extends Event<ED>, ED extends EventDay<E, ?>> implements EventDayService<ED> {

    @Autowired
    private EventDayDao<ED> dao;

    @Autowired
    private EventService<E> eventService;

    @Transactional
    public ED add(Long idEvent, ED day) throws InstanceNotFoundException {
        E event = this.eventService.getById(idEvent);
        day.setNumEvPlaces(0);
        day.setEvent(event);
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
    public void delete(Long idEvent, Long idDay) throws InstanceNotFoundException {
        this.dao.remove(this.getById(idEvent, idDay));
    }

    public List<ED> getAll(Long idEvent, Integer index, Integer count) {
        return this.dao.getAll(idEvent, index, count);
    }

    public List<ED> getByField(String field, Object value, Integer index, Integer count) {
        return this.dao.getListByField(field, value, index, count);
    }

    public List<ED> getByFields(Long idEvent, Long idDay, String filter, String value, Integer index, Integer count)
            throws InstanceNotFoundException {
        Map<String, Object> fields = new HashMap<String, Object>();
        if ((idEvent != null) && (idDay != null)) {
            EventDayPK edPK = new EventDayPK();
            edPK.setIdEvent(idEvent);
            edPK.setIdDay(idDay);
            fields.put("dayPK", edPK);
        } else {
            if (idEvent != null) {
                fields.put("dayPK-idEvent", idEvent);
            }
        }

        if (!(filter.equals("null")) && !(value.equals("null"))) {
            fields.put(filter, value);
        }
        return this.dao.getListByFields(fields, index, count);

    }

    public ED getById(Long idEvent, Long idDay) throws InstanceNotFoundException {
        ED eventDay = this.dao.getById(idEvent, idDay);
        if (eventDay != null) {
            return eventDay;
        } else {
            throw new InstanceNotFoundException(idEvent, "EventDay not found");
        }
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
