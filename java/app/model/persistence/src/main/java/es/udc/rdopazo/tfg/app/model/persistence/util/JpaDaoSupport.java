package es.udc.rdopazo.tfg.app.model.persistence.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
        return this.getListByField(fieldName, value, null, null, null, null);
    }

    public List<E> getListByField(String fieldName, Object value, Integer index, Integer count) {
        return this.getListByField(fieldName, value, null, null, index, count);
    }

    public List<E> getListByField(String fieldName, Object value, OrderingType orderingType, String orderingField) {
        return this.getListByField(fieldName, value, orderingType, orderingField, null, null);
    }

    public List<E> getListByField(String fieldName, Object value, OrderingType orderingType, String orderingField,
            Integer index, Integer count) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<E> root = criteriaQuery.from(this.getEntityClass());

        criteriaQuery.where(criteriaBuilder.equal(root.get(fieldName), value));
        this.setOrder(criteriaBuilder, criteriaQuery, root, orderingType, orderingField);
        TypedQuery<E> typedQuery = this.entityManager.createQuery(criteriaQuery);
        this.setPagination(typedQuery, index, count);
        List<E> result = typedQuery.getResultList();
        return result;
    }

    public List<E> getListByFields(Map<String, Object> fields) {
        return this.getListByFields(fields, null, null);
    }

    public List<E> getListByFields(Map<String, Object> fields, Integer index, Integer count) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<E> root = criteriaQuery.from(this.getEntityClass());
        Predicate where = criteriaBuilder.conjunction();
        String[] parts = null;
        for (Entry<String, Object> field : fields.entrySet()) {
            parts = field.getKey().split("-");
            if (parts.length == 1) {
                where = criteriaBuilder.and(where, criteriaBuilder.equal(root.get(parts[0]), field.getValue()));
            }
            if (parts.length == 2) {
                where = criteriaBuilder.and(where,
                        criteriaBuilder.equal(root.get(parts[0]).get(parts[1]), field.getValue()));
            }
            if (parts.length == 3) {
                where = criteriaBuilder.and(where,
                        criteriaBuilder.equal(root.get(parts[0]).get(parts[1]).get(parts[2]), field.getValue()));
            }

        }
        criteriaQuery.where(where);
        TypedQuery<E> typedQuery = this.entityManager.createQuery(criteriaQuery);
        this.setPagination(typedQuery, index, count);
        List<E> result = typedQuery.getResultList();
        return result;
    }

    protected void setPagination(TypedQuery<E> typedQuery, Integer index, Integer count) {

        if (index != null) {
            typedQuery.setFirstResult(index);
        }

        if (count != null) {
            typedQuery.setMaxResults(count);
        }
    }

    protected void setOrder(CriteriaBuilder criteriaBuilder, CriteriaQuery<E> criteriaQuery, Root<E> root,
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

    public void clearTable() {
        String entity = this.getEntityClass().getSimpleName();
        Query q = this.getEntityManager().createQuery("DELETE FROM " + entity);
        q.executeUpdate();
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
