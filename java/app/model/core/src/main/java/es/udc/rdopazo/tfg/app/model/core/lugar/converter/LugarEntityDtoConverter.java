package es.udc.rdopazo.tfg.app.model.core.lugar.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.api.lugar.dto.LugarDto;
import es.udc.rdopazo.tfg.app.model.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.lugar.JpaLugar;

@Component
public class LugarEntityDtoConverter<D extends LugarDto, L extends Lugar>
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
