package es.udc.rdopazo.tfg.app.model.persistence.api.place.dao;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.util.DaoSupport;

public interface PlaceDao<L extends Place> extends DaoSupport<Long, L> {

    List<L> joinDiaLugarByRouteAndPlace(Long idRoute, String idFoursquare);
}
