package es.udc.rdopazo.tfg.app.model.persistence.api.stay.dao;

import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.util.DaoSupport;

public interface StayDao<V extends Stay<?, ?>> extends DaoSupport<Long, V> {

}
