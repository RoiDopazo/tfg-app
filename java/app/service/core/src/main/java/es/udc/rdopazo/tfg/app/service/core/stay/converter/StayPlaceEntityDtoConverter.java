package es.udc.rdopazo.tfg.app.service.core.stay.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.place.JpaStayPlace;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayPlaceDto;

@Component
public class StayPlaceEntityDtoConverter<D extends StayPlaceDto, S extends Stay<?, ?>>
        extends DefaultEntityDtoConverterSupport<D, S> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaStayPlace.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return StayPlaceDto.class;
    }

}
