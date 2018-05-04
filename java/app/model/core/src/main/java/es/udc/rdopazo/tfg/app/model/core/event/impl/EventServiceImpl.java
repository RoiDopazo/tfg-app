package es.udc.rdopazo.tfg.app.model.core.event.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.EventService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.dao.EventDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.dao.EventDayDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.dao.EventPlaceDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day.id.EventDayPK;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

@Service
public class EventServiceImpl<E extends Event<ED>, ED extends EventDay<E, EP>, EP extends EventPlace<ED>>
        implements EventService<E, ED, EP> {

    @Autowired
    private EventDao<E> eventDao;

    @Autowired
    private EventDayDao<ED> eventDayDao;

    @Autowired
    private EventPlaceDao<EP> eventPlaceDao;

    public List<E> getAllEvents(Integer index, Integer count) {
        return this.eventDao.getAll(index, count);
    }

    public List<E> getEventsByField(String field, Object value, Integer index, Integer count) {
        if (!(field.equals("")) && !(value.equals(""))) {
            return this.eventDao.getListByField(field, value, index, count);
        } else {
            return this.eventDao.getAll(index, count);
        }
    }

    public E getEventById(Long id) throws InstanceNotFoundException {
        E event = this.eventDao.getById(id);
        if (event != null) {
            return event;
        } else {
            throw new InstanceNotFoundException(id, "Event not found");
        }
    }

    @Transactional
    public E addEvent(E event) {
        this.eventDao.add(event);
        return event;
    }

    @Transactional
    public E updateEvent(E event) {
        this.eventDao.update(event);
        return event;
    }

    @Transactional
    public void deleteEvent(Long id) throws InstanceNotFoundException {
        this.eventDao.remove(this.getEventById(id));
    }

    @Transactional
    public ED addEventDay(Long idEvent, ED day) throws InstanceNotFoundException {
        E event = this.getEventById(idEvent);
        day.setNumEvPlaces(0);
        day.setEvent(event);
        event.addDay(day);
        this.eventDayDao.add(day);
        return day;
    }

    @Transactional
    public ED updateEventDay(ED day) {
        this.eventDayDao.update(day);
        return day;
    }

    @Transactional
    public void deleteEventDay(Long idEvent, Long idDay) throws InstanceNotFoundException {
        this.eventDayDao.remove(this.getEventDayById(idEvent, idDay));
    }

    public List<ED> getAllEventDays(Long idEvent, Integer index, Integer count) {
        return this.eventDayDao.getAll(idEvent, index, count);
    }

    public List<ED> getEventDaysByField(String field, Object value, Integer index, Integer count) {
        if (!(field.equals("")) && !(value.equals(""))) {
            return this.eventDayDao.getListByField(field, value, index, count);
        } else {
            return this.eventDayDao.getAll(index, count);
        }
    }

    public List<ED> getEventDaysByFields(Long idEvent, Long idDay, String filter, Object value, Integer index,
            Integer count) throws InstanceNotFoundException {
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

        if (!(filter.equals("")) && !(value.equals(""))) {
            fields.put(filter, value);
        }
        return this.eventDayDao.getListByFields(fields, index, count);

    }

    public ED getEventDayById(Long idEvent, Long idDay) throws InstanceNotFoundException {
        ED eventDay = this.eventDayDao.getById(idEvent, idDay);
        if (eventDay != null) {
            return eventDay;
        } else {
            throw new InstanceNotFoundException(idEvent, "EventDay not found");
        }
    }

    public List<ED> getAllEventDays(Integer index, Integer count) {
        return this.eventDayDao.getAll(index, count);
    }

    public List<ED> getAllEventDaysByDateRange(String city, Date start_date, Date end_date, String type, Integer index,
            Integer count) {
        if (type.equals("BETWEEN")) {
            if ((start_date != null) && (end_date != null)) {
                return this.eventDayDao.getListByDateInBetween(city, start_date, end_date, index, count);
            }
        } else if (type.equals("OVER")) {
            if (end_date != null) {
                return this.eventDayDao.getListByDateInBetween(city, null, end_date, index, count);
            }
        }
        return null;
    }

    public List<EP> getAllEventPlaces(Integer index, Integer count) {
        return this.eventPlaceDao.getAll(index, count);
    }

    public EP getEventPlaceById(Long id) throws InstanceNotFoundException {
        EP eventPlace = this.eventPlaceDao.getById(id);
        if (eventPlace != null) {
            return eventPlace;
        } else {
            throw new InstanceNotFoundException(id, "EventPlace Not Found");
        }
    }

    @Transactional
    public EP addEventPlace(Long idEvent, Long idDay, EP eventPlace) throws InstanceNotFoundException {
        ED eventDay = this.eventDayDao.getById(idEvent, idDay);
        if (eventDay != null) {
            eventPlace.setDay(eventDay);
        } else {
            throw new InstanceNotFoundException(idEvent, "EventDay Not Found");
        }
        this.eventPlaceDao.add(eventPlace);
        return eventPlace;
    }

    @Transactional
    public EP updateEventPlace(EP eventPlace) {
        this.eventPlaceDao.update(eventPlace);
        return eventPlace;
    }

    @Transactional
    public void deleteEventPlace(Long id) throws InstanceNotFoundException {
        this.eventPlaceDao.remove(this.getEventPlaceById(id));

    }

    public List<EP> getEventPlacesByFields(Long idEvent, Long idDay, String filter, Object value, Integer index,
            Integer count) {
        Map<String, Object> fields = new HashMap<String, Object>();
        if ((idEvent != null) && (idDay != null)) {
            ED day = this.eventDayDao.getById(idEvent, idDay);
            fields.put("day", day);
        } else {
            if (idEvent != null) {
                fields.put("day-dayPK-idEvent", idEvent);
            }
        }

        if (!(filter.equals("")) && !(value.equals(""))) {
            fields.put(filter, value);
        }
        return this.eventPlaceDao.getListByFields(fields, index, count);
    }
}
