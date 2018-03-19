package es.udc.rdopazo.tfg.app.model.core.event.day;

import java.util.Date;
import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public interface EventDayService<ED extends EventDay<?, ?>> {

    ED add(Long idEvent, ED day) throws InstanceNotFoundException;

    ED update(ED day);

    void delete(Long idEvent, Long idDay) throws InstanceNotFoundException;

    List<ED> getAll(Long idEvent, Integer index, Integer count);

    List<ED> getAll(Integer index, Integer count);

    List<ED> getByField(String field, Object value, Integer index, Integer count);

    List<ED> getByFields(Long idEvent, Long idDay, String filter, String value, Integer index, Integer count)
            throws InstanceNotFoundException;

    ED getById(Long idEvent, Long idDay) throws InstanceNotFoundException;

    List<ED> getAllByDateRange(Date start_date, Date end_date, String type, Integer index, Integer count);
}
