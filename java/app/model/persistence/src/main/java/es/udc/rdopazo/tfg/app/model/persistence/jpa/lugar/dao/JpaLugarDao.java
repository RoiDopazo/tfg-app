package es.udc.rdopazo.tfg.app.model.persistence.jpa.lugar.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.dao.LugarDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.lugar.JpaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaLugarDao extends JpaDaoSupport<Long, JpaLugar> implements LugarDao<JpaLugar> {

    @Override
    protected Class<JpaLugar> getEntityClass() {
        return JpaLugar.class;
    }

}
