package es.udc.rdopazo.tfg.app.model.core.event.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.EventService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.dao.EventDao;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

@Service
public class EventServiceImpl<E extends Event<?>> implements EventService<E> {

    @Autowired
    private EventDao<E> dao;

    public List<E> getAll(Integer index, Integer count) {
        return this.dao.getAll(index, count);
    }

    public List<E> getByField(String field, Object value, Integer index, Integer count) {
        if (!(field.equals("")) && !(value.equals(""))) {
            return this.dao.getListByField(field, value, index, count);
        } else {
            return this.dao.getAll(index, count);
        }
    }

    public E getById(Long id) throws InstanceNotFoundException {
        E event = this.dao.getById(id);
        if (event != null) {
            return event;
        } else {
            throw new InstanceNotFoundException(id, "Event not found");
        }
    }

    @Transactional
    public E add(E event) {
        this.dao.add(event);
        return event;
    }

    @Transactional
    public E update(E event) {
        this.dao.update(event);
        return event;
    }

    @Transactional
    public void delete(Long id) throws InstanceNotFoundException {
        this.dao.remove(this.getById(id));
    }

}
