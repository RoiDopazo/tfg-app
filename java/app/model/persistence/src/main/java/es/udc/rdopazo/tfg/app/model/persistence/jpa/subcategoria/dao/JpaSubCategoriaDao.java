package es.udc.rdopazo.tfg.app.model.persistence.jpa.subcategoria.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.subcategoria.dao.SubCategoriaDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.subcategoria.JpaSubCategoria;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaSubCategoriaDao extends JpaDaoSupport<Long, JpaSubCategoria>
        implements SubCategoriaDao<JpaSubCategoria> {

    @Override
    protected Class<JpaSubCategoria> getEntityClass() {
        return JpaSubCategoria.class;
    }

}
