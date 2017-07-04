package es.udc.rdopazo.tfg.app.model.persistence.jpa.categoria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.jpa.categoria.JpaCategoria;

@Repository
public class JpaCategoriaDao {

    @Autowired
    private EntityManager entityManager;

    public List<JpaCategoria> getAll() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaCategoria> criteriaQuery = criteriaBuilder.createQuery(JpaCategoria.class);

        Root<JpaCategoria> root = criteriaQuery.from(JpaCategoria.class);
        criteriaQuery.select(root);

        List<JpaCategoria> result = this.entityManager.createQuery(criteriaQuery).getResultList();
        return result;
    }
}
