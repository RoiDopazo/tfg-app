package es.udc.rdopazo.tfg.app.model.persistence.api.event.dao;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.util.DaoSupport;

public interface EventDao<E extends Event<?>> extends DaoSupport<Long, E> {

}
