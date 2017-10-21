package es.udc.rdopazo.tfg.app.model.persistence.api.lugar.dao;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.util.DaoSupport;

public interface LugarDao<L extends Lugar> extends DaoSupport<Long, L> {

    List<L> joinDiaLugarByRouteAndPlace(Long idRoute, String idFoursquare);
}
