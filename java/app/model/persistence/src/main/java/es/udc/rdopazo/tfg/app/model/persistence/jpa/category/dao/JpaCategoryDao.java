package es.udc.rdopazo.tfg.app.model.persistence.jpa.category.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.category.dao.CategoryDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.category.JpaCategory;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaCategoryDao extends JpaDaoSupport<Long, JpaCategory> implements CategoryDao<JpaCategory> {

    @Override
    protected Class<JpaCategory> getEntityClass() {
        return JpaCategory.class;
    }

}
