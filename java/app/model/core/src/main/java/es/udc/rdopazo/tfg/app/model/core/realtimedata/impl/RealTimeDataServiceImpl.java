package es.udc.rdopazo.tfg.app.model.core.realtimedata.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.realtimedata.RealTimeDataService;
import es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata.RealTimeData;
import es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata.dao.RealTimeDataDao;

@Service
public class RealTimeDataServiceImpl<RTD extends RealTimeData> implements RealTimeDataService<RTD> {

    @Autowired
    private RealTimeDataDao<RTD> dao;

    public List<RTD> getAll() {
        return this.dao.getAll();
    }

    public RTD add(RTD realTimeData) {
        RTD rtdPersist = this.getById(realTimeData.getId());
        rtdPersist.appendLocations(realTimeData.getLocations());
        this.dao.add(rtdPersist);
        return rtdPersist;
    }

    public RTD getById(Long id) {
        return this.dao.getById(id);
    }

    public List<RTD> getByField(String field, String value, Integer index, Integer count) {
        // TODO Auto-generated method stub
        return null;
    }

    public RTD update(RTD realTimeData) {
        // TODO Auto-generated method stub
        return null;
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

}
