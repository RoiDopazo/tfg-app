package es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.dao;

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
import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.dao.RouteDayDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.JpaRouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.id.RouteDayPK;
import es.udc.rdopazo.tfg.app.model.persistence.util.OrderingType;

@Repository
public class JpaRouteDayDao implements RouteDayDao<JpaRouteDay> {

    @Autowired
    EntityManager entityManager;

    private Class<JpaRouteDay> getEntityClass() {
        return JpaRouteDay.class;
    }

    public void add(JpaRouteDay entidad) {
        if (this.entityManager.contains(entidad)) {
            this.entityManager.merge(entidad);
        } else {
            this.entityManager.persist(entidad);
        }
    }

    public void update(JpaRouteDay entidad) {
        this.entityManager.merge(entidad);

    }

    public void remove(JpaRouteDay entidad) {
        this.entityManager.remove(entidad);

    }

    public List<JpaRouteDay> getAll(Integer index, Integer count) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaRouteDay> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaRouteDay> root = criteriaQuery.from(this.getEntityClass());
        criteriaQuery.select(root);
        TypedQuery<JpaRouteDay> typedQuery = this.entityManager.createQuery(criteriaQuery);

        this.setPagination(typedQuery, index, count);
        List<JpaRouteDay> result = typedQuery.getResultList();
        return result;
    }

    public List<JpaRouteDay> getAll(Long idRoute) {
        return this.getAll(idRoute, null, null);
    }

    public List<JpaRouteDay> getAll(Long idRoute, Integer index, Integer count) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaRouteDay> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaRouteDay> root = criteriaQuery.from(this.getEntityClass());

        criteriaQuery.where(criteriaBuilder.equal(root.get("diaPK").get("idRoute"), idRoute));

        TypedQuery<JpaRouteDay> typedQuery = this.entityManager.createQuery(criteriaQuery);

        this.setPagination(typedQuery, index, count);
        List<JpaRouteDay> result = typedQuery.getResultList();
        return result;
    }

    public JpaRouteDay getById(Long idRoute, Long idDay) {
        RouteDayPK pk = new RouteDayPK(idRoute, idDay);
        return (this.entityManager.find(this.getEntityClass(), pk));
    }

    private void setPagination(TypedQuery<JpaRouteDay> typedQuery, Integer index, Integer count) {

        if (index != null) {
            typedQuery.setFirstResult(index);
        }

        if (count != null) {
            typedQuery.setMaxResults(count);
        }
    }

    public List<JpaRouteDay> getListByField(String fieldName, Object value) {
        return this.getListByField(fieldName, value, null, null, null, null);
    }

    public List<JpaRouteDay> getListByField(String fieldName, Object value, Integer index, Integer count) {
        return this.getListByField(fieldName, value, null, null, index, count);
    }

    public List<JpaRouteDay> getListByField(String fieldName, Object value, OrderingType orderingType,
            String orderingField) {
        return this.getListByField(fieldName, value, orderingType, orderingField, null, null);
    }

    public List<JpaRouteDay> getListByField(String fieldName, Object value, OrderingType orderingType,
            String orderingField, Integer index, Integer count) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaRouteDay> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaRouteDay> root = criteriaQuery.from(this.getEntityClass());

        criteriaQuery.where(criteriaBuilder.equal(root.get(fieldName), value));
        this.setOrder(criteriaBuilder, criteriaQuery, root, orderingType, orderingField);
        TypedQuery<JpaRouteDay> typedQuery = this.entityManager.createQuery(criteriaQuery);
        this.setPagination(typedQuery, index, count);
        List<JpaRouteDay> result = typedQuery.getResultList();
        return result;
    }

    public List<JpaRouteDay> getListByFields(Map<String, Object> fields, Integer index, Integer count) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaRouteDay> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaRouteDay> root = criteriaQuery.from(this.getEntityClass());
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
        TypedQuery<JpaRouteDay> typedQuery = this.entityManager.createQuery(criteriaQuery);
        this.setPagination(typedQuery, index, count);
        List<JpaRouteDay> result = typedQuery.getResultList();
        return result;
    }

    private void setOrder(CriteriaBuilder criteriaBuilder, CriteriaQuery<JpaRouteDay> criteriaQuery,
            Root<JpaRouteDay> root, OrderingType orderingType, String orderingField) {

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
        Query q = this.entityManager.createQuery("DELETE FROM JpaRouteDay");
        q.executeUpdate();
    }

}
