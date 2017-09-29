package es.udc.rdopazo.tfg.app.model.core.ruta.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.ruta.RutaService;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.dao.RutaDao;

@Service
public class RutaServiceImpl<R extends Ruta<?>> implements RutaService<R> {

    @Autowired
    RutaDao<R> dao;

    public List<R> getAll(Integer index, Integer count) {
        return this.dao.getAll(index, count);
    }

    public R getById(Long id) {
        return this.dao.getById(id);
    }

    @Transactional
    public R add(R ruta) {
        this.dao.add(ruta);
        return ruta;
    }

    @Transactional
    public R update(R ruta) {
        this.dao.update(ruta);
        return ruta;
    }

    @Transactional
    public void delete(Long id) {
        this.dao.remove(this.dao.getById(id));
    }

}
