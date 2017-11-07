package es.udc.rdopazo.tfg.app.model.core.dialugar.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.dialugar.DiaLugarService;
import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.dia.dao.DiaDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.DiaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.dao.DiaLugarDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.dao.LugarDao;
import es.udc.rdopazo.tfg.app.model.persistence.util.OrderingType;

@Service
public class DiaLugarServiceImpl<L extends Lugar, D extends Dia<DL>, DL extends DiaLugar<D, L>>
        implements DiaLugarService<DL> {

    @Autowired
    private DiaLugarDao<DL> dao;

    @Autowired
    private DiaDao<D> diaDao;

    @Autowired
    private LugarDao<L> lugarDao;

    public List<DL> getAll(Integer index, Integer count) {
        return this.dao.getAll(index, count);
    }

    // Ordered
    public List<DL> getAllInDay(Long idRoute, Long idDay) {
        D day = this.diaDao.getById(idRoute, idDay);
        return this.dao.getListByField("day", day, OrderingType.ASC, "order");
    }

    public List<DL> getByRouteAndDayAndPlace(Long idRoute, Long idDay, Long idPlace) {

        Map<String, Object> fields = new HashMap<String, Object>();
        D day = this.diaDao.getById(idRoute, idDay);
        fields.put("day", day);
        fields.put("place", idPlace);
        return this.dao.getListByFields(fields);
    }

    public DL getById(Long id) {
        return this.dao.getById(id);
    }

    @Transactional
    public DL add(DL dayPlace) {
        dayPlace.setTime(0L);
        dayPlace.setTravelDistance(0L);
        dayPlace.setTravelTime(0L);
        dayPlace.setTravelMode("WALKING");
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

    // Obtiene el mayor valor del orden entre los lugares de un dia (usado para fijar el orden al insertar)
    public Integer getMaxOrderNum(Long idRoute, Long idDay) {
        return (this.getAllInDay(idRoute, idDay).size() + 1);
    }

    @Transactional
    public void fixOrdersAfterDelete(Long idRoute, Long idDay) {
        List<DL> dayPlaces = this.getAllInDay(idRoute, idDay);
        int index = 1;
        for (DL dayPlace : dayPlaces) {
            dayPlace.setOrder(index);
            index++;
            this.update(dayPlace);
        }
    }

}
