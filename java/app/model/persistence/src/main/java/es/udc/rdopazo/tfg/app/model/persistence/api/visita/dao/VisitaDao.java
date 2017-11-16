package es.udc.rdopazo.tfg.app.model.persistence.api.visita.dao;

import es.udc.rdopazo.tfg.app.model.persistence.api.visita.Visita;
import es.udc.rdopazo.tfg.app.model.persistence.util.DaoSupport;

public interface VisitaDao<V extends Visita<?, ?>> extends DaoSupport<Long, V> {

}
