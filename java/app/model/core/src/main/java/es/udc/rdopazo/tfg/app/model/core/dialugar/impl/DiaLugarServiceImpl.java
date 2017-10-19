package es.udc.rdopazo.tfg.app.model.core.dialugar.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.dialugar.DiaLugarService;
import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.dia.dao.DiaDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.DiaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.dao.DiaLugarDao;
import es.udc.rdopazo.tfg.app.model.persistence.util.OrderingType;

@Service
public class DiaLugarServiceImpl<D extends Dia, DL extends DiaLugar<?, ?>> implements DiaLugarService<DL> {

    @Autowired
    private DiaLugarDao<DL> dao;

    @Autowired
    private DiaDao<D> diaDao;

    public List<DL> getAll(Integer index, Integer count) {
        return this.dao.getAll(index, count);
    }

    // Ordered
    public List<DL> getAllInDay(Long idRoute, Long idDay) {
        D day = this.diaDao.getById(idRoute, idDay);
        return this.dao.getListByField("day", day, OrderingType.ASC, "order");
    }

    public DL getById(Long id) {
        return this.dao.getById(id);
    }

    @Transactional
    public DL add(DL dayPlace) {
        this.dao.add(dayPlace);
        return dayPlace;
    }

    @Transactional
    public void delete(Long id) {
        this.dao.remove(this.dao.getById(id));

    }

    @Transactional
    public DL update(DL dayPlace) {
        this.dao.update(dayPlace);
        return dayPlace;
    }

}
