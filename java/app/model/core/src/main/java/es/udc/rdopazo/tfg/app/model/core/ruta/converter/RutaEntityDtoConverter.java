package es.udc.rdopazo.tfg.app.model.core.ruta.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.api.ruta.dto.RutaDto;
import es.udc.rdopazo.tfg.app.model.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.ruta.JpaRuta;

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
