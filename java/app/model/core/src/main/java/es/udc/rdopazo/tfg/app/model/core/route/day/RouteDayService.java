package es.udc.rdopazo.tfg.app.model.core.route.day;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public interface RouteDayService<R extends Route<D, ?>, D extends RouteDay<?>> {

    D add(R rotue);

    D add(R rotue, Long startTime, String realTimeData);

    List<D> createDays(R route, Integer numDays) throws InstanceNotFoundException;

    D update(D day);

    void delete(Long idRoute, Long idDay) throws InstanceNotFoundException;

    List<D> getAll(Long idRoute, Integer index, Integer count);

    List<D> getAll(Integer index, Integer count);

    D getById(Long idRoute, Long idDay) throws InstanceNotFoundException;

    List<Long> getListDaysByRotueAndPlace(Long idRoute, String idFoursquare);

    List<D> getByField(String field, String value, Integer index, Integer count) throws InputValidationException;

    List<D> getByFields(Long idRoute, String filter, Object value, Integer index, Integer count);

}