package es.udc.rdopazo.tfg.app.model.core.place;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;

public interface PlaceService<P extends Place> {

    public List<P> getAll();

    public P getByField(String fieldName, Object value);

    public P getById(Long id);

    public P add(P place);

    public P update(P place);

    public void delete(Long id);
}
