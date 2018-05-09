package es.udc.rdopazo.tfg.app.model.persistence.jpa.category.dao;

import javax.persistence.Query;

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

    public void clear() {
        Query q = this.getEntityManager().createQuery("DELETE FROM JpaSubCategory");
        q.executeUpdate();
        q = this.getEntityManager().createQuery("DELETE FROM JpaCategory");
        q.executeUpdate();
    }

}
