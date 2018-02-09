package es.udc.rdopazo.tfg.app.model.core.route.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.dao.RouteDao;

@Service
public class RouteServiceImpl<R extends Route<?>> implements RouteService<R> {

    @Autowired
    RouteDao<R> dao;

    public List<R> getAll(Integer index, Integer count) {
        return this.dao.getAll(index, count);
    }

    @PostAuthorize("11 == authentication.principal")
    public R getById(Long id) {
        return this.dao.getById(id);
    }

    @Transactional
    public R add(R ruta) {
        ruta.setDistance(0L);
        ruta.setTime(0L);
        ruta.setNumDays(0);
        ruta.setNumPlaces(0);
        ruta.setState("PENDIENTE");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        ruta.setCreationDate(date);
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
