package es.udc.rdopazo.tfg.app.model.persistence.api.route.dao;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.util.DaoSupport;
import es.udc.rdopazo.tfg.app.util.enums.RouteState;

public interface RouteDao<R extends Route<?, ?>> extends DaoSupport<Long, R> {

    List<R> explore(Long idUser, String city, RouteState state, Long numDays, Long maxDistance, Long maxDuration,
            Integer index, Integer count);
}
