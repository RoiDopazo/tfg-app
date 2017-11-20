package es.udc.rdopazo.tfg.app.model.core.route.day;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;

public interface RouteDayService<R extends Route<D>, D extends RouteDay> {

    D add(R rotue, D day);

    List<D> createDays(R route, Integer numDays);

    D update(D day);

    void delete(Long idRoute, Long idDay);

    List<D> getAll(Long idRoute, Integer index, Integer count);

    D getById(Long idRoute, Long idDay);

    List<Long> getListDaysByRotueAndPlace(Long idRoute, String idFoursquare);

}
