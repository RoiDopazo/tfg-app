package es.udc.rdopazo.tfg.app.service.core.stay.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.JpaStay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.event.JpaStayEvent;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.stay.place.JpaStayPlace;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayEventPlaceDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayPlaceDto;

@Component
public class StayEntityDtoConverter<D extends StayDto, S extends Stay<?, ?, ?>>
        extends DefaultEntityDtoConverterSupport<D, S> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaStay.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return StayDto.class;
    }

    public S toEntityP(StayPlaceDto dto) {
        @SuppressWarnings("unchecked")
        S entity = (S) this.getModelMapperSupport().getModelMapper().map(dto, JpaStayPlace.class);
        return entity;
    }

    public S toEntityE(StayEventPlaceDto dto) {
        @SuppressWarnings("unchecked")
        S entity = (S) this.getModelMapperSupport().getModelMapper().map(dto, JpaStayEvent.class);
        return entity;
    }
}
