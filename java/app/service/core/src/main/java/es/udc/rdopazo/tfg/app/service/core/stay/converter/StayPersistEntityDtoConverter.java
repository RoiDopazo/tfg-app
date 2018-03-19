package es.udc.rdopazo.tfg.app.service.core.stay.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.JpaStay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.event.JpaStayEvent;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.place.JpaStayPlace;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayPersistDto;

@Component
public class StayPersistEntityDtoConverter<S extends Stay<?, ?, ?>, DT extends StayPersistDto>
        extends DefaultEntityDtoConverterSupport<DT, S> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaStay.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return StayPersistDto.class;
    }

    @Override
    public DT toDto(S entity) {
        @SuppressWarnings("unchecked")
        DT dto = (DT) this.getModelMapperSupport().getModelMapper().map(entity, this.getDtoClass());
        dto.setIdRoute(entity.getDay().getDiaPK().getIdRoute());
        dto.setIdDay(entity.getDay().getDiaPK().getIdDay());
        if ((entity.getType() == "PL") && (entity.getPlace() != null)) {
            dto.setIdPlace(entity.getPlace().getId());
        }
        if ((entity.getType() == "EV") && (entity.getEventPlace() != null)) {
            dto.setIdEventPlace(entity.getEventPlace().getId());
        }

        return dto;
    }

    public S toEntityE(DT dto) {
        dto.setIdPlace(null);
        @SuppressWarnings("unchecked")
        S entity = (S) this.getModelMapperSupport().getModelMapper().map(dto, JpaStayEvent.class);
        return entity;
    }

    public S toEntityP(DT dto) {
        dto.setIdEventPlace(null);
        @SuppressWarnings("unchecked")
        S entity = (S) this.getModelMapperSupport().getModelMapper().map(dto, JpaStayPlace.class);
        return entity;
    }

}
