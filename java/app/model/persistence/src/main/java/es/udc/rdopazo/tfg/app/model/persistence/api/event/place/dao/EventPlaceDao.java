package es.udc.rdopazo.tfg.app.model.persistence.api.event.place.dao;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.util.DaoSupport;

public interface EventPlaceDao<EP extends EventPlace<?>> extends DaoSupport<Long, EP> {

}
