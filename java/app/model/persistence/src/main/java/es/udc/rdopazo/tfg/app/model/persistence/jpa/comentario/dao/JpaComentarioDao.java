package es.udc.rdopazo.tfg.app.model.persistence.jpa.comentario.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.comentario.dao.ComentarioDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.comentario.JpaComentario;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaComentarioDao extends JpaDaoSupport<Long, JpaComentario> implements ComentarioDao<JpaComentario> {

    @Override
    protected Class<JpaComentario> getEntityClass() {
        return JpaComentario.class;
    }

}
