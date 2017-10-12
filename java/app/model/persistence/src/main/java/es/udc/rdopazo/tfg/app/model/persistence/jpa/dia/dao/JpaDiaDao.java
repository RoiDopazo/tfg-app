package es.udc.rdopazo.tfg.app.model.persistence.jpa.dia.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.dia.dao.DiaDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.dia.JpaDia;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.dia.id.DiaPK;

@Repository
public class JpaDiaDao implements DiaDao<JpaDia> {

    @Autowired
    EntityManager entityManager;

    private Class<JpaDia> getEntityClass() {
        return JpaDia.class;
    }

    public void add(JpaDia entidad) {
        if (this.entityManager.contains(entidad)) {
            this.entityManager.merge(entidad);
        } else {
            this.entityManager.persist(entidad);
        }
    }

    public void update(JpaDia entidad) {
        this.add(entidad);

    }

    public void remove(JpaDia entidad) {
        this.entityManager.remove(entidad);

    }

    public List<JpaDia> getAll(Long idRoute) {
        return this.getAll(idRoute, null, null);
    }

    public List<JpaDia> getAll(Long idRoute, Integer index, Integer count) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaDia> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaDia> root = criteriaQuery.from(this.getEntityClass());

        criteriaQuery.where(criteriaBuilder.equal(root.get("diaPK").get("idRoute"), idRoute));

        TypedQuery<JpaDia> typedQuery = this.entityManager.createQuery(criteriaQuery);

        this.setPagination(typedQuery, index, count);
        List<JpaDia> result = typedQuery.getResultList();
        return result;
    }

    public JpaDia getById(Long idRoute, Long idDay) {
        DiaPK pk = new DiaPK(idRoute, idDay);
        return (this.entityManager.find(this.getEntityClass(), pk));
    }

    public List<JpaDia> getListByField(String fieldName, Object value) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaDia> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaDia> root = criteriaQuery.from(this.getEntityClass());

        criteriaQuery.where(criteriaBuilder.equal(root.get(fieldName), value));

        return (this.entityManager.createQuery(criteriaQuery).getResultList());
    }

    private void setPagination(TypedQuery<JpaDia> typedQuery, Integer index, Integer count) {

        if (index != null) {
            typedQuery.setFirstResult(index);
        }

        if (count != null) {
            typedQuery.setMaxResults(count);
        }
    }
}
