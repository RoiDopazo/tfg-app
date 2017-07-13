package es.udc.rdopazo.tfg.app.model.persistence.jpa.usuario.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.dao.UsuarioDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.usuario.JpaUsuario;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaUsuarioDao extends JpaDaoSupport<Long, JpaUsuario> implements UsuarioDao<JpaUsuario> {

    @Override
    protected Class<JpaUsuario> getEntityClass() {
        return JpaUsuario.class;
    }

}
