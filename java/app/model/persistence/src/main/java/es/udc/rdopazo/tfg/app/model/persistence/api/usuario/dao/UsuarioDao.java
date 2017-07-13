package es.udc.rdopazo.tfg.app.model.persistence.api.usuario.dao;

import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.model.persistence.util.DaoSupport;

public interface UsuarioDao<U extends Usuario> extends DaoSupport<Long, U> {

}
