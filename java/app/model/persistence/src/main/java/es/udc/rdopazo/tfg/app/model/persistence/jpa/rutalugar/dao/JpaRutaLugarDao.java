package es.udc.rdopazo.tfg.app.model.persistence.jpa.rutalugar.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar.dao.RutaLugarDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.rutalugar.JpaRutaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaRutaLugarDao extends JpaDaoSupport<Long, JpaRutaLugar> implements RutaLugarDao<JpaRutaLugar> {

    @Override
    protected Class<JpaRutaLugar> getEntityClass() {
        return JpaRutaLugar.class;
    }

}
