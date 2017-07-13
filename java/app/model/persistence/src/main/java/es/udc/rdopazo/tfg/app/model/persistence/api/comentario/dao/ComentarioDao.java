package es.udc.rdopazo.tfg.app.model.persistence.api.comentario.dao;

import es.udc.rdopazo.tfg.app.model.persistence.api.comentario.Comentario;
import es.udc.rdopazo.tfg.app.model.persistence.util.DaoSupport;

public interface ComentarioDao<C extends Comentario> extends DaoSupport<Long, C> {

}
