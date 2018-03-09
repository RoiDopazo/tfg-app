package es.udc.rdopazo.tfg.app.service.core.route.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.JpaRoute;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.route.dto.RoutePersistDto;

@Component
public class RoutePersistEntityDtoConverter<D extends RouteDay<?>, DT extends RoutePersistDto, R extends Route<D, ?>>
        extends DefaultEntityDtoConverterSupport<DT, R> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaRoute.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return RoutePersistDto.class;
    }

    @Override
    public DT toDto(R entity) {
        @SuppressWarnings("unchecked")
        DT dto = (DT) this.getModelMapperSupport().getModelMapper().map(entity, this.getDtoClass());
        dto.setUserId(entity.getUser().getId());
        return dto;
    }

}
