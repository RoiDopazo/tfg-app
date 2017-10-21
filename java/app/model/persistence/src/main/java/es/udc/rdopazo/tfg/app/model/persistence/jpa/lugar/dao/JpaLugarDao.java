package es.udc.rdopazo.tfg.app.model.persistence.jpa.lugar.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.dao.LugarDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.dialugar.JpaDiaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.lugar.JpaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaLugarDao extends JpaDaoSupport<Long, JpaLugar> implements LugarDao<JpaLugar> {

    @Override
    protected Class<JpaLugar> getEntityClass() {
        return JpaLugar.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return super.getEntityManager();
    }

    public List<JpaLugar> joinDiaLugarByRouteAndPlace(Long idRoute, String idFoursquare) {
        CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<JpaLugar> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaDiaLugar> root = criteriaQuery.from(JpaDiaLugar.class);
        Root<JpaLugar> root2 = criteriaQuery.from(JpaLugar.class);

        Predicate where = criteriaBuilder.conjunction();
        where = criteriaBuilder.equal(root.get("day").get("diaPK").get("idRoute"), idRoute);
        where = criteriaBuilder.and(where, criteriaBuilder.equal(root2.get("idFoursquare"), idFoursquare));
        Join<JpaDiaLugar, JpaLugar> places = root.join("place");
        criteriaQuery.select(places).where(where);

        List<JpaLugar> results = this.getEntityManager().createQuery(criteriaQuery).getResultList();
        return results;
    }
}
