package es.udc.rdopazo.tfg.app.model.persistence.api.event.day.dao;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;

public interface EventDayDao<ED extends EventDay<?>> {

    void add(ED entidad);

    void update(ED entidad);

    void remove(ED entidad);

    List<ED> getAll(Long idEvent);

    List<ED> getAll(Long idEvent, Integer index, Integer count);

    ED getById(Long idEvent, Long idDay);

    List<ED> getListByField(String fieldName, Object value);
}
