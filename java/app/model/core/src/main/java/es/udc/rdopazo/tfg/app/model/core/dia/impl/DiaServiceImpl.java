package es.udc.rdopazo.tfg.app.model.core.dia.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.dia.DiaService;
import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.dia.dao.DiaDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;

@Service
public class DiaServiceImpl<R extends Ruta<D>, D extends Dia> implements DiaService<R, D> {

    @Autowired
    DiaDao<D> dao;

    @Transactional
    public D add(R route, D day) {
        route.addDay(day);
        this.dao.add(day);
        return day;
    }

    @Transactional
    public D update(D day) {
        this.dao.update(day);
        return day;
    }

    public void delete(Long idRoute, Long idDay) {
        this.dao.remove(this.dao.getById(idRoute, idDay));
    }

    public List<D> getAll(Long idRoute, Integer index, Integer count) {
        return this.dao.getAll(idRoute, index, count);
    }

    public D getById(Long idRoute, Long idDay) {
        return this.dao.getById(idRoute, idDay);
    }

}
