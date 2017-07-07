package es.udc.rdopazo.tfg.app.model.persistence.jpa.categoria.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.dao.CategoriaDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.categoria.JpaCategoria;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaCategoriaDao extends JpaDaoSupport<Long, JpaCategoria> implements CategoriaDao<JpaCategoria> {

    @Override
    protected Class<JpaCategoria> getEntityClass() {
        return JpaCategoria.class;
    }

}
