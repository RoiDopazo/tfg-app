package es.udc.rdopazo.tfg.app.service.core.event.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.JpaEvent;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.event.dto.EventPersistDto;

@Component
public class EventPersistEntityDtoConverter<E extends Event<?>, DT extends EventPersistDto>
        extends DefaultEntityDtoConverterSupport<DT, E> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaEvent.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return EventPersistDto.class;
    }

}
