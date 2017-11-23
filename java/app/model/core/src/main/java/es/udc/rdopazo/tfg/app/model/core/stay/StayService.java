package es.udc.rdopazo.tfg.app.model.core.stay;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;

public interface StayService<S extends Stay<?, ?>> {

    List<S> getAll(Integer index, Integer count);

    List<S> getByRouteAndDayAndPlace(Long idRoute, Long idDay, Long idPlace);

    List<S> getAllInDay(Long idRoute, Long idDay);

    S getById(Long id);

    S add(S stay);

    S update(S stay);

    void delete(Long id);

    Integer getMaxOrderNum(Long idRoute, Long idDay);

    void fixOrdersAfterDelete(Long idRoute, Long idDay);
}
