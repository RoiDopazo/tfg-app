package es.udc.rdopazo.tfg.app.model.persistence.jpa.route.dao;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.dao.RouteDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.JpaRoute;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Repository
public class JpaRouteDao extends JpaDaoSupport<Long, JpaRoute> implements RouteDao<JpaRoute> {

    @Override
    protected Class<JpaRoute> getEntityClass() {
        return JpaRoute.class;
    }

}
