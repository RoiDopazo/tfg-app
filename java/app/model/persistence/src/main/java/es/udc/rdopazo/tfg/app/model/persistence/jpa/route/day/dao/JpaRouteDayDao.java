package es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.dao.RouteDayDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.JpaRouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.id.RouteDayPK;

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
        this.add(entidad);

    }

    public void remove(JpaRouteDay entidad) {
        this.entityManager.remove(entidad);

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

    public List<JpaRouteDay> getListByField(String fieldName, Object value) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaRouteDay> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaRouteDay> root = criteriaQuery.from(this.getEntityClass());

        criteriaQuery.where(criteriaBuilder.equal(root.get(fieldName), value));

        return (this.entityManager.createQuery(criteriaQuery).getResultList());
    }

    private void setPagination(TypedQuery<JpaRouteDay> typedQuery, Integer index, Integer count) {

        if (index != null) {
            typedQuery.setFirstResult(index);
        }

        if (count != null) {
            typedQuery.setMaxResults(count);
        }
    }
}
