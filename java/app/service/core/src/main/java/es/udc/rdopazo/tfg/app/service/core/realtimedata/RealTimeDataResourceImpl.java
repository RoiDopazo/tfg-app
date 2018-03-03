package es.udc.rdopazo.tfg.app.service.core.realtimedata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.realtimedata.RealTimeDataService;
import es.udc.rdopazo.tfg.app.model.core.route.day.RouteDayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata.RealTimeData;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.service.core.realtimedata.converter.RealTimeDataEntityDtoConverter;
import es.udc.rdopazo.tfg.service.api.realtimedata.RealTimeDataResource;
import es.udc.rdopazo.tfg.service.api.realtimedata.dto.RealTimeDataDto;
import es.udc.rdopazo.tfg.service.api.realtimedata.dto.RealTimeDataPersistDto;

@Service
public class RealTimeDataResourceImpl<R extends Route<D, ?>, D extends RouteDay<?, RTD>, RTD extends RealTimeData<D>>
        implements RealTimeDataResource {

    @Autowired
    private RealTimeDataService<RTD, D> service;

    @Autowired
    private RouteDayService<R, D> routeDayService;

    @Autowired
    private RealTimeDataEntityDtoConverter<RTD, RealTimeDataDto> converter;

    public List<RealTimeDataDto> getAll() {
        return this.converter.toDtoList(this.service.getAll());
    }

    public RealTimeDataDto create(RealTimeDataDto realTimeDataDto) {
        RTD realTimeData = this.converter.toEntity(realTimeDataDto);
        // R r = this.rutaService.add(ruta);
        // return this.converter.toDto(r);
        return null;
    }

    public void add(String idRoute, String idDay, RealTimeDataPersistDto rtdDto) {
        D routeDay = null;
        try {
            routeDay = this.routeDayService.getById(Long.parseLong(idRoute), Long.parseLong(idDay));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (routeDay.getRealTimeData() == null) {
            RTD entity = this.converter.toPersistEntity(rtdDto);
            this.service.add(routeDay, entity);
            System.out.println("Es null");
        } else {

        }
    }

}
