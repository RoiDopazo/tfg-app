package es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata.dao;

import es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata.RealTimeData;
import es.udc.rdopazo.tfg.app.model.persistence.util.DaoSupport;

public interface RealTimeDataDao<RTD extends RealTimeData<?>> extends DaoSupport<Long, RTD> {

}
