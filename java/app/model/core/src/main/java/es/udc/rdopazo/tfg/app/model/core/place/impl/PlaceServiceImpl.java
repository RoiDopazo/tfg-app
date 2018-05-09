package es.udc.rdopazo.tfg.app.model.core.place.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.place.PlaceService;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.dao.PlaceDao;

@Service
public class PlaceServiceImpl<P extends Place> implements PlaceService<P> {

    @Autowired
    PlaceDao<P> dao;

    public List<P> getAll() {
        return this.dao.getAll();
    }

    public P getById(Long id) {
        return this.dao.getById(id);
    }

    @Transactional
    public P add(P place) {
        this.dao.add(place);
        return place;
    }

    @Transactional
    public P update(P place) {
        this.dao.update(place);
        return place;
    }

    @Transactional
    public void delete(Long id) {
        this.dao.remove(this.getById(id));
    }

    public List<P> getListByField(String fieldName, Object value, Integer index, Integer count) {
        if (fieldName.equals("verified")) {
            boolean b = Boolean.parseBoolean((String) value);
            return this.dao.getListByField(fieldName, b, index, count);
        } else {
            if (!(fieldName.equals("")) && !(value.equals(""))) {
                return this.dao.getListByField(fieldName, value, index, count);
            } else {
                return this.dao.getAll(index, count);
            }
        }
    }

}
