package es.udc.rdopazo.tfg.app.service.core.realtimedata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.realtimedata.RealTimeDataService;
import es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata.RealTimeData;
import es.udc.rdopazo.tfg.app.service.core.realtimedata.converter.RealTimeDataEntityDtoConverter;
import es.udc.rdopazo.tfg.service.api.realtimedata.RealTimeDataResource;
import es.udc.rdopazo.tfg.service.api.realtimedata.dto.RealTimeDataDto;

@Service
public class RealTimeDataResourceImpl<RTD extends RealTimeData> implements RealTimeDataResource {

    @Autowired
    private RealTimeDataService<RTD> service;

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

    public RealTimeDataDto add(RealTimeDataDto realTimeDataDto) {
        System.out.println(realTimeDataDto.getId());
        System.out.println(realTimeDataDto.getLocations());
        return null;
    }

}
