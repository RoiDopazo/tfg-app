package es.udc.rdopazo.tfg.app.service.core.lugar.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.lugar.JpaLugar;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.lugar.dto.LugarDto;

@Component
public class LugarEntityDtoConverter<D extends LugarDto, L extends Lugar<?>>
        extends DefaultEntityDtoConverterSupport<D, L> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaLugar.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return LugarDto.class;
    }
}
