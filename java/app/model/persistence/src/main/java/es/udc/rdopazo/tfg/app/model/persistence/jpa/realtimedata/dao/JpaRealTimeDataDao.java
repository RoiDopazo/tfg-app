package es.udc.rdopazo.tfg.app.model.persistence.jpa.realtimedata.dao;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata.dao.RealTimeDataDao;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.realtimedata.JpaRealTimeData;
import es.udc.rdopazo.tfg.app.model.persistence.util.JpaDaoSupport;

@Component
public class JpaRealTimeDataDao extends JpaDaoSupport<Long, JpaRealTimeData>
        implements RealTimeDataDao<JpaRealTimeData> {

    @Override
    protected Class<JpaRealTimeData> getEntityClass() {
        return JpaRealTimeData.class;
    }

}
