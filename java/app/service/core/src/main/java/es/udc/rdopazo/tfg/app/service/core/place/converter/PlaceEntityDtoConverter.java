package es.udc.rdopazo.tfg.app.service.core.place.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.place.JpaPlace;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.place.dto.PlaceDto;

@Component
public class PlaceEntityDtoConverter<D extends PlaceDto, L extends Place>
        extends DefaultEntityDtoConverterSupport<D, L> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaPlace.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return PlaceDto.class;
    }
}
