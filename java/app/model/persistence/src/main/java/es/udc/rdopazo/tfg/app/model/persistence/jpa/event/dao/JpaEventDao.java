package es.udc.rdopazo.tfg.app.model.persistence.jpa.event.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.dao.EventDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.JpaEvent;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaEventDao extends JpaDaoSupport<Long, JpaEvent> implements EventDao<JpaEvent> {

    @Override
    protected Class<JpaEvent> getEntityClass() {
        return JpaEvent.class;
    }

}
