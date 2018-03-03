package es.udc.rdopazo.tfg.app.model.core.realtimedata;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata.RealTimeData;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;

public interface RealTimeDataService<RTD extends RealTimeData<RD>, RD extends RouteDay<?, RTD>> {

    List<RTD> getAll();

    RTD add(RD routeDay, RTD realTimeData);

    RTD getById(Long id);

    List<RTD> getByField(String field, String value, Integer index, Integer count);

    RTD update(RTD realTimeData);

    void delete(Long id);
}
