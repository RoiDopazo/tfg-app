package es.udc.rdopazo.tfg.app.model.core.realtimedata;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata.RealTimeData;

public interface RealTimeDataService<RTD extends RealTimeData> {

    List<RTD> getAll();

    RTD add(RTD realTimeData);

    RTD getById(Long id);

    List<RTD> getByField(String field, String value, Integer index, Integer count);

    RTD update(RTD realTimeData);

    void delete(Long id);
}
