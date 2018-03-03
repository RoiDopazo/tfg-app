package es.udc.rdopazo.tfg.app.service.core.realtimedata.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata.RealTimeData;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.realtimedata.JpaRealTimeData;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.realtimedata.dto.RealTimeDataDto;
import es.udc.rdopazo.tfg.service.api.realtimedata.dto.RealTimeDataPersistDto;

@Component
public class RealTimeDataEntityDtoConverter<RTD extends RealTimeData<?>, D extends RealTimeDataDto>
        extends DefaultEntityDtoConverterSupport<D, RTD> {

    @Override
    protected Class<?> getEntityClass() {
        return RealTimeData.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return RealTimeDataDto.class;
    }

    public RTD toPersistEntity(RealTimeDataPersistDto dto) {
        @SuppressWarnings("unchecked")
        RTD entity = (RTD) new JpaRealTimeData();
        String locations = new StringBuilder().append("[{'lat':").append(dto.getLat()).append(",'lng:'")
                .append(dto.getLng()).append("}]").toString();
        System.out.println(locations);
        entity.setLocations(locations);

        return entity;
    }

}
