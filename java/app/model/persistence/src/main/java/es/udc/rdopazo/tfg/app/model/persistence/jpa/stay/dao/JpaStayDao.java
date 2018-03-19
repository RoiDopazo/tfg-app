package es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.stay.dao.StayDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.JpaStay;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaStayDao extends JpaDaoSupport<Long, JpaStay> implements StayDao<JpaStay> {

    @Autowired
    EntityManager entityManager;

    @Override
    protected Class<JpaStay> getEntityClass() {
        return JpaStay.class;
    }

    @Override
    public List<JpaStay> getListByFields(Map<String, Object> fields, Integer index, Integer count) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<JpaStay> criteriaQuery = criteriaBuilder.createQuery(this.getEntityClass());
        Root<JpaStay> root = criteriaQuery.from(this.getEntityClass());
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
        TypedQuery<JpaStay> typedQuery = this.entityManager.createQuery(criteriaQuery);
        this.setPagination(typedQuery, index, count);
        List<JpaStay> result = typedQuery.getResultList();
        return result;
    }

}
