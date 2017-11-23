package es.udc.rdopazo.tfg.app.model.persistence.jpa.user.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.dao.UsuarioDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.user.JpaUser;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaUserDao extends JpaDaoSupport<Long, JpaUser> implements UsuarioDao<JpaUser> {

    @Override
    protected Class<JpaUser> getEntityClass() {
        return JpaUser.class;
    }

}
