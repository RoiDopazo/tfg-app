package es.udc.rdopazo.tfg.etravel.engine.model.jpa.category.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.etravel.engine.model.jpa.category.JpaCategory;

@Repository
public class JpaCategoryDao {

    @Autowired
    private EntityManager entityManager;

    public List<JpaCategory> getAll() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaCategory> criteriaQuery = criteriaBuilder.createQuery(JpaCategory.class);

        Root<JpaCategory> root = criteriaQuery.from(JpaCategory.class);
        criteriaQuery.select(root);

        List<JpaCategory> result = this.entityManager.createQuery(criteriaQuery).getResultList();
        return result;
    }
}
