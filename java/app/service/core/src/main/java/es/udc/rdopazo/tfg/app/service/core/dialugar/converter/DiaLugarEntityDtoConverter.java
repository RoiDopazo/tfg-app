package es.udc.rdopazo.tfg.app.service.core.dialugar.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.DiaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.dialugar.JpaDiaLugar;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.dialugar.dto.DiaLugarDto;

@Component
public class DiaLugarEntityDtoConverter<D extends DiaLugarDto, DL extends DiaLugar<?, ?>>
        extends DefaultEntityDtoConverterSupport<D, DL> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaDiaLugar.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return DiaLugarDto.class;
    }
}
