package es.udc.rdopazo.tfg.app.model.persistence.jpa.visita.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.visita.dao.VisitaDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.visita.JpaVisita;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaVisitaDao extends JpaDaoSupport<Long, JpaVisita> implements VisitaDao<JpaVisita> {

    @Override
    protected Class<JpaVisita> getEntityClass() {
        return JpaVisita.class;
    }

}
