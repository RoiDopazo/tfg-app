package es.udc.rdopazo.tfg.app.service.core.event.day.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day.JpaEventDay;
import es.udc.rdopazo.tfg.app.service.core.util.ModelMapperSupport;
import es.udc.rdopazo.tfg.service.api.event.day.dto.EventDayPersistDto;

@Component
public class EventDayPersistEntityDtoConverter<DTO extends EventDayPersistDto, E extends EventDay<?, ?>> {

    @Autowired
    ModelMapperSupport modelMapper;

    protected Class<?> getEntityClass() {
        return JpaEventDay.class;
    }

    protected Class<?> getDtoClass() {
        return EventDayPersistDto.class;
    }

    public E toEntity(DTO dto) {
        @SuppressWarnings("unchecked")
        E entity = (E) this.modelMapper.getModelMapper().map(dto, this.getEntityClass());
        return entity;
    }

    public DTO toDto(E entity) {
        @SuppressWarnings("unchecked")
        DTO dto = (DTO) new EventDayPersistDto();
        dto.setIdEvent(entity.getDayPK().getIdEvent());
        dto.setIdDay(entity.getDayPK().getIdDay());
        dto.setDate(entity.getDate());
        dto.setNumEvPlaces(entity.getNumEvPlaces());
        return dto;
    }

    public List<DTO> toDtoList(List<E> entities) {
        List<DTO> dtoList = new ArrayList<DTO>();
        for (E entity : entities) {
            dtoList.add(this.toDto(entity));
        }
        return dtoList;
    }

    public List<E> toEntityList(List<DTO> dtos) {
        List<E> entityList = new ArrayList<E>();
        for (DTO dto : dtos) {
            entityList.add(this.toEntity(dto));
        }
        return entityList;
    }

    public ModelMapperSupport getModelMapperSupport() {
        return this.modelMapper;
    }
}
