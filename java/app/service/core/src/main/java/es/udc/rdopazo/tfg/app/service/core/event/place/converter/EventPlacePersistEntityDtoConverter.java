package es.udc.rdopazo.tfg.app.service.core.event.place.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.place.JpaEventPlace;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.event.place.dto.EventPlacePersistDto;

@Component
public class EventPlacePersistEntityDtoConverter<EP extends EventPlace<?>, DT extends EventPlacePersistDto>
        extends DefaultEntityDtoConverterSupport<DT, EP> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaEventPlace.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return EventPlacePersistDto.class;
    }

    @Override
    public DT toDto(EP entity) {
        @SuppressWarnings("unchecked")
        DT dto = (DT) new EventPlacePersistDto();
        dto.setId(entity.getId());
        dto.setAddress(entity.getAddress());
        dto.setDescription(entity.getDescription());
        dto.setEndHour(entity.getEndHour());
        dto.setLat(entity.getLat());
        dto.setLng(entity.getLng());
        dto.setName(entity.getName());
        dto.setStartHour(entity.getStartHour());
        dto.setIdEvent(entity.getDay().getDayPK().getIdEvent());
        dto.setIdDay(entity.getDay().getDayPK().getIdDay());

        return dto;
    }

}
