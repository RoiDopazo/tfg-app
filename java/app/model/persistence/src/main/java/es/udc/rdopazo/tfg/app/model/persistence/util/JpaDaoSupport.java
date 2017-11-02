package es.udc.rdopazo.tfg.app.model.persistence.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class JpaDaoSupport<PK extends Serializable, E extends Entity<PK>> implements DaoSupport<PK, E> {

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
        this.entityManager.merge(entidad);
    }

    public void remove(E entidad) {
        this.entityManager.remove(entidad);
    }

    public List<E> getAll() {
        return this.getAll(null, null);
    }

    public List<E> getAll(Integer index, Integer count) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<E> root = criteriaQuery.from(this.getEntityClass());
        criteriaQuery.select(root);
        TypedQuery<E> typedQuery = this.entityManager.createQuery(criteriaQuery);
        this.setPagination(typedQuery, index, count);
        List<E> result = typedQuery.getResultList();
        return result;
    }

    public E getById(PK id) {
        return (this.entityManager.find(this.getEntityClass(), id));
    }

    public List<E> getListByField(String fieldName, Object value) {
        return this.getListByField(fieldName, value, null, null);
    }

    public List<E> getListByField(String fieldName, Object value, OrderingType orderingType, String orderingField) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<E> root = criteriaQuery.from(this.getEntityClass());

        criteriaQuery.where(criteriaBuilder.equal(root.get(fieldName), value));
        this.setOrder(criteriaBuilder, criteriaQuery, root, orderingType, orderingField);
        TypedQuery<E> typedQuery = this.entityManager.createQuery(criteriaQuery);
        List<E> result = typedQuery.getResultList();
        return result;
    }

    public List<E> getListByFields(Map<String, Object> fields) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<E> root = criteriaQuery.from(this.getEntityClass());
        Predicate where = criteriaBuilder.conjunction();
        for (Entry<String, Object> field : fields.entrySet()) {
            where = criteriaBuilder.and(where, criteriaBuilder.equal(root.get(field.getKey()), field.getValue()));
        }
        criteriaQuery.where(where);
        return (this.entityManager.createQuery(criteriaQuery).getResultList());
    }

    private void setPagination(TypedQuery<E> typedQuery, Integer index, Integer count) {

        if (index != null) {
            typedQuery.setFirstResult(index);
        }

        if (count != null) {
            typedQuery.setMaxResults(count);
        }
    }

    private void setOrder(CriteriaBuilder criteriaBuilder, CriteriaQuery<E> criteriaQuery, Root<E> root,
            OrderingType orderingType, String orderingField) {

        if ((orderingType != null) && (orderingField != null)) {
            Order order;
            if (orderingType.equals(OrderingType.ASC)) {
                order = criteriaBuilder.asc(root.get(orderingField));
            } else {
                order = criteriaBuilder.desc(root.get(orderingField));
            }
            criteriaQuery.orderBy(new Order[] { order });
        }
    }

    /**
     * Returns the entityManager
     *
     * @return The entityManager
     */
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    /**
     * Sets the entityManager to given value
     *
     * @param entityManager
     *            The entityManager to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
