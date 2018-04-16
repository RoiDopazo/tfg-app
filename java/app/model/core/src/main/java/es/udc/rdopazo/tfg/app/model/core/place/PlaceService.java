package es.udc.rdopazo.tfg.app.model.core.place;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;

public interface PlaceService<P extends Place> {

    public List<P> getAll();

    public List<P> getListByField(String fieldName, Object value, Integer index, Integer count);

    public P getById(Long id);

    public P add(P place);

    public P update(P place);

    public void delete(Long id);
}
