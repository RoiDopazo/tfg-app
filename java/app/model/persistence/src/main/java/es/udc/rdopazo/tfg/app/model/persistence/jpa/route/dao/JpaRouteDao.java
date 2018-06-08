package es.udc.rdopazo.tfg.app.model.persistence.jpa.route.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.dao.RouteDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.JpaRoute;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;
import es.udc.rdopazo.tfg.app.model.persistence.util.OrderingType;
import es.udc.rdopazo.tfg.app.util.enums.RouteState;

@Repository
public class JpaRouteDao extends JpaDaoSupport<Long, JpaRoute> implements RouteDao<JpaRoute> {

    @Override
    protected Class<JpaRoute> getEntityClass() {
        return JpaRoute.class;
    }

    public List<JpaRoute> explore(Long idUser, String city, RouteState state, Long numDays, Long maxDistance,
            Long maxDuration, Integer index, Integer count) {
        CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<JpaRoute> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaRoute> root = criteriaQuery.from(this.getEntityClass());
        List<Predicate> predicates = new ArrayList<Predicate>();

        if (!(idUser == null)) {
            Predicate predicate = criteriaBuilder.notEqual(root.get("user").get("id"), idUser);
            predicates.add(predicate);
        }

        if (!city.equals("")) {
            Predicate predicate1 = criteriaBuilder.equal(root.get("city"), city);
            predicates.add(predicate1);
        }
        if (!(state == null)) {
            Predicate predicate2 = criteriaBuilder.equal(root.get("state"), state);
            predicates.add(predicate2);
        }

        if (!(numDays == null)) {
            Predicate predicate3 = criteriaBuilder.equal(root.<Long> get("numDays"), numDays);
            predicates.add(predicate3);
        }
        if (!(maxDistance == null)) {
            Predicate predicate4 = criteriaBuilder.lessThan(root.<Long> get("distance"), maxDistance);
            predicates.add(predicate4);
        }
        if (!(maxDuration == null)) {
            Predicate predicate5 = criteriaBuilder.lessThan(root.<Long> get("time"), maxDuration);
            predicates.add(predicate5);
        }
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));

        this.setOrder(criteriaBuilder, criteriaQuery, root, OrderingType.ASC, "startDate");
        TypedQuery<JpaRoute> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
        this.setPagination(typedQuery, index, count);
        return (typedQuery.getResultList());
    }

}
