package es.udc.rdopazo.tfg.app.model.core.realtimedata.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.realtimedata.RealTimeDataService;
import es.udc.rdopazo.tfg.app.model.core.route.day.RouteDayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata.RealTimeData;
import es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata.dao.RealTimeDataDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;

@Service
public class RealTimeDataServiceImpl<RTD extends RealTimeData<RD>, RD extends RouteDay<?, RTD>>
        implements RealTimeDataService<RTD, RD> {

    @Autowired
    private RealTimeDataDao<RTD> dao;

    @Autowired
    private RouteDayService<?, RD> rdService;

    public List<RTD> getAll() {
        return this.dao.getAll();
    }

    @Transactional
    public RTD add(RD routeDay, RTD realTimeData) {
        realTimeData.setRouteDay(routeDay);
        this.dao.add(realTimeData);
        return realTimeData;
    }

    public RTD getById(Long id) {
        return this.dao.getById(id);
    }

    public List<RTD> getByField(String field, String value, Integer index, Integer count) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    public RTD update(RTD realTimeData) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

}
