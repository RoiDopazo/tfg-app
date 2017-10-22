package es.udc.rdopazo.tfg.app.model.core.dialugar;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.DiaLugar;

public interface DiaLugarService<DL extends DiaLugar> {

    List<DL> getAll(Integer index, Integer count);

    List<DL> getByRouteAndDayAndPlace(Long idRoute, Long idDay, Long idPlace);

    List<DL> getAllInDay(Long idRoute, Long idDay);

    DL getById(Long id);

    DL add(DL dayPlace);

    DL update(DL dayPlace);

    void delete(Long id);
}
