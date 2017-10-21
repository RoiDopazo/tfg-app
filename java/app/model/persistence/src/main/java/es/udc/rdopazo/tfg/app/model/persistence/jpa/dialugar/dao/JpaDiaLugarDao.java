package es.udc.rdopazo.tfg.app.model.persistence.jpa.dialugar.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.dao.DiaLugarDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.dialugar.JpaDiaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaDiaLugarDao extends JpaDaoSupport<Long, JpaDiaLugar> implements DiaLugarDao<JpaDiaLugar> {

    @Override
    protected Class<JpaDiaLugar> getEntityClass() {
        return JpaDiaLugar.class;
    }
}
