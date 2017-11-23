package es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.dao.EventDayDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day.JpaEventDay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day.id.EventDayPK;

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
        this.add(entidad);

    }

    public void remove(JpaEventDay entidad) {
        this.entityManager.remove(entidad);

    }

    public List<JpaEventDay> getAll(Long idEvent) {
        return this.getAll(idEvent, null, null);
    }

    public List<JpaEventDay> getAll(Long idEvent, Integer index, Integer count) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaEventDay> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaEventDay> root = criteriaQuery.from(this.getEntityClass());

        criteriaQuery.where(criteriaBuilder.equal(root.get("dayPK").get("idEvent"), idEvent));

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
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaEventDay> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaEventDay> root = criteriaQuery.from(this.getEntityClass());

        criteriaQuery.where(criteriaBuilder.equal(root.get(fieldName), value));

        return (this.entityManager.createQuery(criteriaQuery).getResultList());
    }

    private void setPagination(TypedQuery<JpaEventDay> typedQuery, Integer index, Integer count) {

        if (index != null) {
            typedQuery.setFirstResult(index);
        }

        if (count != null) {
            typedQuery.setMaxResults(count);
        }
    }
}
