package es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day.dao;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.dao.EventDayDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day.JpaEventDay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day.id.EventDayPK;
import es.udc.rdopazo.tfg.app.model.persistence.util.OrderingType;

@Repository
public class JpaEventDayDao implements EventDayDao<JpaEventDay> {

    @Autowired
    EntityManager entityManager;

    private Class<JpaEventDay> getEntityClass() {
        return JpaEventDay.class;
    }

    public void add(JpaEventDay entidad) {
        if (this.entityManager.contains(entidad)) {
            this.entityManager.merge(entidad);
        } else {
            this.entityManager.persist(entidad);
        }
    }

    public void update(JpaEventDay entidad) {
        this.entityManager.merge(entidad);

    }

    public void remove(JpaEventDay entidad) {
        this.entityManager.remove(entidad);

    }

    public List<JpaEventDay> getAll(Long idEvent) {
        return this.getAll(idEvent, null, null);
    }

    public List<JpaEventDay> getAll(Integer index, Integer count) {
        return this.getAll(null, index, count);
    }

    public List<JpaEventDay> getAll(Long idEvent, Integer index, Integer count) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaEventDay> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaEventDay> root = criteriaQuery.from(this.getEntityClass());

        if (idEvent != null) {
            criteriaQuery.where(criteriaBuilder.equal(root.get("dayPK").get("idEvent"), idEvent));
        }

        TypedQuery<JpaEventDay> typedQuery = this.entityManager.createQuery(criteriaQuery);

        this.setPagination(typedQuery, index, count);
        List<JpaEventDay> result = typedQuery.getResultList();
        return result;
    }

    public JpaEventDay getById(Long idEvent, Long idDay) {
        EventDayPK pk = new EventDayPK(idEvent, idDay);
        return (this.entityManager.find(this.getEntityClass(), pk));
    }

    public List<JpaEventDay> getListByField(String fieldName, Object value) {
        return this.getListByField(fieldName, value, null, null);
    }

    public List<JpaEventDay> getListByField(String fieldName, Object value, Integer index, Integer count) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaEventDay> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaEventDay> root = criteriaQuery.from(this.getEntityClass());

        criteriaQuery.where(criteriaBuilder.equal(root.get(fieldName), value));
        TypedQuery<JpaEventDay> typedQuery = this.entityManager.createQuery(criteriaQuery);
        this.setPagination(typedQuery, index, count);
        return typedQuery.getResultList();
    }

    public List<JpaEventDay> getListByDateInBetween(String city, Date left_value, Date right_value, Integer index,
            Integer count) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaEventDay> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaEventDay> root = criteriaQuery.from(this.getEntityClass());
        List<Predicate> predicates = new ArrayList<Predicate>();

        if (!city.equals("")) {
            Predicate predicate3 = criteriaBuilder.equal(root.get("event").get("city"), city);
            predicates.add(predicate3);
        }
        if (left_value == null) {
            Predicate predicate1 = criteriaBuilder.greaterThan(root.<Date> get("date"), right_value);
            predicates.add(predicate1);
        } else {
            Predicate p1 = criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("date"), left_value);
            Predicate p2 = criteriaBuilder.lessThanOrEqualTo(root.<Date> get("date"), right_value);
            // Predicate inBetween = criteriaBuilder.between(root.<Date> get("date"), left_value, right_value);
            predicates.add(p1);

            predicates.add(p2);
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));

        this.setOrder(criteriaBuilder, criteriaQuery, root, OrderingType.ASC, "date");
        TypedQuery<JpaEventDay> typedQuery = this.entityManager.createQuery(criteriaQuery);
        this.setPagination(typedQuery, index, count);
        return (typedQuery.getResultList());
    }

    public List<JpaEventDay> getListByFields(Map<String, Object> fields, Integer index, Integer count) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaEventDay> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaEventDay> root = criteriaQuery.from(this.getEntityClass());
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
        TypedQuery<JpaEventDay> typedQuery = this.entityManager.createQuery(criteriaQuery);
        this.setPagination(typedQuery, index, count);
        List<JpaEventDay> result = typedQuery.getResultList();
        return result;
    }

    private void setPagination(TypedQuery<JpaEventDay> typedQuery, Integer index, Integer count) {

        if (index != null) {
            typedQuery.setFirstResult(index);
        }

        if (count != null) {
            typedQuery.setMaxResults(count);
        }
    }

    private void setOrder(CriteriaBuilder criteriaBuilder, CriteriaQuery<JpaEventDay> criteriaQuery,
            Root<JpaEventDay> root, OrderingType orderingType, String orderingField) {

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

}
