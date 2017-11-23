package es.udc.rdopazo.tfg.app.model.core.event.day;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;

public interface EventDayService<ED extends EventDay<?>> {

    ED add(Long idEvent, ED day);

    ED update(ED day);

    void delete(Long idEvent, Long idDay);

    List<ED> getAll(Long idEvent, Integer index, Integer count);

    ED getById(Long idEvent, Long idDay);

}
