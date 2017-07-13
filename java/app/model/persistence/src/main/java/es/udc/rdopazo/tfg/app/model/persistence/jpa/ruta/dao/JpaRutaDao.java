package es.udc.rdopazo.tfg.app.model.persistence.jpa.ruta.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.dao.RutaDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.ruta.JpaRuta;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaRutaDao extends JpaDaoSupport<Long, JpaRuta> implements RutaDao<JpaRuta> {

    @Override
    protected Class<JpaRuta> getEntityClass() {
        return JpaRuta.class;
    }

}
