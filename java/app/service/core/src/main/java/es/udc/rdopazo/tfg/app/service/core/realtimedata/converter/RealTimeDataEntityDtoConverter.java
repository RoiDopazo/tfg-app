package es.udc.rdopazo.tfg.app.service.core.realtimedata.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata.RealTimeData;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.realtimedata.dto.RealTimeDataDto;

@Component
public class RealTimeDataEntityDtoConverter<RTD extends RealTimeData, D extends RealTimeDataDto>
        extends DefaultEntityDtoConverterSupport<D, RTD> {

    @Override
    protected Class<?> getEntityClass() {
        return RealTimeData.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return RealTimeDataDto.class;
    }

}
