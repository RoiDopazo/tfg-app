package es.udc.rdopazo.tfg.app.model.persistence.api.route.dao;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.util.DaoSupport;

public interface RouteDao<R extends Route<?>> extends DaoSupport<Long, R> {

}
