package es.udc.rdopazo.tfg.app.model.core.event.place.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.place.EventPlaceService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.dao.EventDayDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.dao.EventPlaceDao;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

@Service
public class EventPlaceServiceImpl<EP extends EventPlace<ED>, ED extends EventDay<?, ?>>
        implements EventPlaceService<EP> {

    @Autowired
    private EventPlaceDao<EP> dao;

    @Autowired
    private EventDayDao<ED> eventDayDao;

    public List<EP> getAll(Integer index, Integer count) {
        return this.dao.getAll(index, count);
    }

    public EP getById(Long id) throws InstanceNotFoundException {
        EP eventPlace = this.dao.getById(id);
        if (eventPlace != null) {
            return eventPlace;
        } else {
            throw new InstanceNotFoundException(id, "EventPlace Not Found");
        }
    }

    @Transactional
    public EP add(Long idEvent, Long idDay, EP eventPlace) throws InstanceNotFoundException {
        ED eventDay = this.eventDayDao.getById(idEvent, idDay);
        if (eventDay != null) {
            eventPlace.setDay(eventDay);
        } else {
            throw new InstanceNotFoundException(idEvent, "EventDay Not Found");
        }
        this.dao.add(eventPlace);
        return eventPlace;
    }

    @Transactional
    public EP update(EP eventPlace) {
        this.dao.update(eventPlace);
        return eventPlace;
    }

    @Transactional
    public void delete(Long id) throws InstanceNotFoundException {
        this.dao.remove(this.getById(id));

    }

    public List<EP> getByFields(Long idEvent, Long idDay, String filter, String value, Integer index, Integer count) {
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
        return this.dao.getListByFields(fields, index, count);
    }

}
