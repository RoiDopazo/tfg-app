package es.udc.rdopazo.tfg.app.model.core.lugar.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.lugar.LugarService;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.dao.LugarDao;

@Service
public class LugarServiceImpl<L extends Lugar<?>> implements LugarService<L> {

    @Autowired
    LugarDao<L> dao;

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

}
