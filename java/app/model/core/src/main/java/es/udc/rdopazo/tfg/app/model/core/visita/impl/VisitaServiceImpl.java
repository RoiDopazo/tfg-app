package es.udc.rdopazo.tfg.app.model.core.visita.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.visita.VisitaService;
import es.udc.rdopazo.tfg.app.model.persistence.api.visita.Visita;
import es.udc.rdopazo.tfg.app.model.persistence.api.visita.dao.VisitaDao;

@Service
public class VisitaServiceImpl<V extends Visita<?, ?>> implements VisitaService<V> {

    @Autowired
    private VisitaDao<V> dao;

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
