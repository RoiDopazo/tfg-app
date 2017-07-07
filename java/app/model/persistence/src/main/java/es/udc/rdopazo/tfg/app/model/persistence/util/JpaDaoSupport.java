package es.udc.rdopazo.tfg.app.model.persistence.util;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class JpaDaoSupport<PK extends Serializable, E extends Entidad<PK>> implements DaoSupport<PK, E> {

    @Autowired
    EntityManager entityManager;

    protected abstract Class<E> getEntityClass();

    public void add(E entidad) {
        if (this.entityManager.contains(entidad)) {
            this.entityManager.merge(entidad);
        } else {
            this.entityManager.persist(entidad);
        }
    }

    public void update(E entidad) {
        this.add(entidad);
    }

    public void remove(E entidad) {
        this.entityManager.remove(entidad);
    }

    public List<E> getAll() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<E> root = criteriaQuery.from(this.getEntityClass());
        criteriaQuery.select(root);

        List<E> result = this.entityManager.createQuery(criteriaQuery).getResultList();
        return result;
    }

    public E getById(PK id) {
        return (this.entityManager.find(this.getEntityClass(), id));
    }

    public List<E> getListByField(String fieldName, Object value) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<E> root = criteriaQuery.from(this.getEntityClass());

        criteriaQuery.where(criteriaBuilder.equal(root.get(fieldName), value));

        return (this.entityManager.createQuery(criteriaQuery).getResultList());
    }

}
