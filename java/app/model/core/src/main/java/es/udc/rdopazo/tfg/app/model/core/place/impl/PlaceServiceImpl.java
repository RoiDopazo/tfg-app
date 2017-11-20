package es.udc.rdopazo.tfg.app.model.core.place.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.place.PlaceService;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.dao.PlaceDao;

@Service
public class PlaceServiceImpl<L extends Place> implements PlaceService<L> {

    @Autowired
    PlaceDao<L> dao;

    public List<L> getAll() {
        return this.dao.getAll();
    }

    public L getById(Long id) {
        return this.dao.getById(id);
    }

    @Transactional
    public L add(L lugar) {
        this.dao.add(lugar);
        return lugar;
    }

    @Transactional
    public L update(L lugar) {
        this.dao.update(lugar);
        return lugar;
    }

    @Transactional
    public void delete(Long id) {
        this.dao.remove(this.dao.getById(id));
    }

    public L getByField(String fieldName, Object value) {
        List<L> entities = this.dao.getListByField(fieldName, value);
        if (entities.size() != 0) {
            return entities.get(0);
        } else {
            return null;
        }
    }

}
