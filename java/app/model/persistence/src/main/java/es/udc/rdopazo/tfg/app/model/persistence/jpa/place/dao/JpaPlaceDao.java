package es.udc.rdopazo.tfg.app.model.persistence.jpa.place.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.place.dao.PlaceDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.place.JpaPlace;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.JpaStay;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaPlaceDao extends JpaDaoSupport<Long, JpaPlace> implements PlaceDao<JpaPlace> {

    @Override
    protected Class<JpaPlace> getEntityClass() {
        return JpaPlace.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return super.getEntityManager();
    }

    public List<JpaPlace> joinStayPlaceByRouteAndPlace(Long idRoute, String idFoursquare) {
        CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<JpaPlace> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaStay> root = criteriaQuery.from(JpaStay.class);
        Root<JpaPlace> root2 = criteriaQuery.from(JpaPlace.class);

        Predicate where = criteriaBuilder.conjunction();
        where = criteriaBuilder.equal(root.get("day").get("diaPK").get("idRoute"), idRoute);
        where = criteriaBuilder.and(where, criteriaBuilder.equal(root2.get("idFoursquare"), idFoursquare));
        Join<JpaStay, JpaPlace> places = root.join("place");
        criteriaQuery.select(places).where(where);

        List<JpaPlace> results = this.getEntityManager().createQuery(criteriaQuery).getResultList();
        return results;
    }
}
