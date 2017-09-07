package es.udc.rdopazo.tfg.app.service.core.ruta.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.ruta.JpaRuta;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.ruta.dto.RutaDto;

@Component
public class RutaEntityDtoConverter<D extends RutaDto, R extends Ruta<?>>
        extends DefaultEntityDtoConverterSupport<D, R> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaRuta.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return RutaDto.class;
    }
}
