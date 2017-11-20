package es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.stay.dao.StayDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.JpaStay;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaStayDao extends JpaDaoSupport<Long, JpaStay> implements StayDao<JpaStay> {

    @Override
    protected Class<JpaStay> getEntityClass() {
        return JpaStay.class;
    }

}
