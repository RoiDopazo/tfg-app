package es.udc.rdopazo.tfg.app.model.core.event.place;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;

public interface EventPlaceService<EP extends EventPlace> {

    List<EP> getAll(Integer index, Integer count);

    EP getById(Long id);

    EP add(EP eventPlace);

    EP update(EP eventPlace);

    void delete(Long id);
}
