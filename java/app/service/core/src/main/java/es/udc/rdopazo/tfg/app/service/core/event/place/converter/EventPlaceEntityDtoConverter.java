package es.udc.rdopazo.tfg.app.service.core.event.place.converter;

import org.springframework.stereotype.Repository;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.place.JpaEventPlace;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.event.place.dto.EventPlaceDto;

@Repository
public class EventPlaceEntityDtoConverter<D extends EventPlaceDto, E extends EventPlace<?>>
        extends DefaultEntityDtoConverterSupport<D, E> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaEventPlace.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return EventPlaceDto.class;
    }

}
