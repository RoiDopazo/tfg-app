package es.udc.rdopazo.tfg.app.service.core.event.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.JpaEvent;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.event.dto.EventDto;

@Component
public class EventEntityDtoConverter<D extends EventDto, E extends Event<?>>
        extends DefaultEntityDtoConverterSupport<D, E> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaEvent.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return EventDto.class;
    }

}