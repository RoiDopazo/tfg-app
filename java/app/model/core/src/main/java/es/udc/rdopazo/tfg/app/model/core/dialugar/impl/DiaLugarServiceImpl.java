package es.udc.rdopazo.tfg.app.model.core.dialugar.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.dialugar.DiaLugarService;
import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.dia.dao.DiaDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.DiaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.dao.DiaLugarDao;

@Service
public class DiaLugarServiceImpl<D extends Dia, DL extends DiaLugar<?>> implements DiaLugarService<DL> {

    @Autowired
    private DiaLugarDao<DL> dao;

    @Autowired
    private DiaDao<D> diaDao;

    public List<DL> getAll(Integer index, Integer count) {
        return this.dao.getAll(index, count);
    }

    public List<DL> getAllInDay(Long idRoute, Long idDay) {
        D day = this.diaDao.getById(idRoute, idDay);
        return this.dao.getListByField("day", day);
    }

    public DL getById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    public DL add(DL dayPlace) {
        // TODO Auto-generated method stub
        return null;
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

    public DL update(DL dayPlace) {
        // TODO Auto-generated method stub
        return null;
    }

}
