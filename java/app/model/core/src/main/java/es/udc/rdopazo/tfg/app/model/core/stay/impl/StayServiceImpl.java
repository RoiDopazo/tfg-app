package es.udc.rdopazo.tfg.app.model.core.stay.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.stay.StayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.dao.StayDao;

@Service
public class StayServiceImpl<V extends Stay<?, ?>> implements StayService<V> {

    @Autowired
    private StayDao<V> dao;

    public List<V> getAll() {
        return this.dao.getAll();
    }

    public V getById(Long id) {
        return this.dao.getById(id);
    }

    @Transactional
    public V add(V visita) {
        this.dao.add(visita);
        return visita;
    }

    @Transactional
    public V update(V visita) {
        this.dao.update(visita);
        return visita;
    }

    @Transactional
    public void delete(Long id) {
        this.dao.remove(this.dao.getById(id));
    }

}
