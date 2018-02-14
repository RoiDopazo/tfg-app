package es.udc.rdopazo.tfg.app.model.core.event.place.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.event.place.EventPlaceService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.dao.EventPlaceDao;

@Service
public class EventPlaceServiceImpl<EP extends EventPlace<?>> implements EventPlaceService<EP> {

    @Autowired
    private EventPlaceDao<EP> dao;

    public List<EP> getAll(Integer index, Integer count) {
        return this.dao.getAll(index, count);
    }

    public EP getById(Long id) {
        return this.dao.getById(id);
    }

    @Transactional
    public EP add(EP eventPlace) {
        this.dao.add(eventPlace);
        return eventPlace;
    }

    @Transactional
    public EP update(EP eventPlace) {
        this.dao.update(eventPlace);
        return eventPlace;
    }

    @Transactional
    public void delete(Long id) {
        this.dao.remove(this.getById(id));

    }

}
