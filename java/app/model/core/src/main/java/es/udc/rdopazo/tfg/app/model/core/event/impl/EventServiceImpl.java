package es.udc.rdopazo.tfg.app.model.core.event.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.EventService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.dao.EventDao;

@Service
public class EventServiceImpl<E extends Event<?>> implements EventService<E> {

    @Autowired
    private EventDao<E> dao;

    public List<E> getAll(Integer index, Integer count) {
        return this.dao.getAll(index, count);
    }

    public E getById(Long id) {
        return this.dao.getById(id);
    }

    @Transactional
    public E add(E event) {
        this.dao.add(event);
        return event;
    }

    @Transactional
    public E update(E event) {
        this.dao.add(event);
        return event;
    }

    @Transactional
    public void delete(Long id) {
        this.dao.remove(this.dao.getById(id));
    }

}
