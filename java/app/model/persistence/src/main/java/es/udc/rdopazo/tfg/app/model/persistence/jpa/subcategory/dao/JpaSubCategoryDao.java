package es.udc.rdopazo.tfg.app.model.persistence.jpa.subcategory.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.dao.SubCategoryDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.subcategory.JpaSubCategory;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaSubCategoryDao extends JpaDaoSupport<Long, JpaSubCategory> implements SubCategoryDao<JpaSubCategory> {

    @Override
    protected Class<JpaSubCategory> getEntityClass() {
        return JpaSubCategory.class;
    }

}
