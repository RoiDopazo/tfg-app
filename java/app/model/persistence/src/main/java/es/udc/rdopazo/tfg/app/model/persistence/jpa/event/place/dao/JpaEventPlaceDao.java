package es.udc.rdopazo.tfg.app.model.persistence.jpa.event.place.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.dao.EventPlaceDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.place.JpaEventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaEventPlaceDao extends JpaDaoSupport<Long, JpaEventPlace> implements EventPlaceDao<JpaEventPlace> {

    @Override
    protected Class<JpaEventPlace> getEntityClass() {
        return JpaEventPlace.class;
    }

}
