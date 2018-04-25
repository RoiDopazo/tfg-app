package es.udc.rdopazo.tfg.app.service.core.route.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.JpaRoute;
import es.udc.rdopazo.tfg.app.service.core.route.day.converter.RouteDayEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.route.day.dto.RouteDayDto;
import es.udc.rdopazo.tfg.service.api.route.dto.RouteDto;

@Component
public class RouteEntityDtoConverter<D extends RouteDay<S>, DT extends RouteDto, R extends Route<D, ?>, S extends Stay<?, ?, ?>>
        extends DefaultEntityDtoConverterSupport<DT, R> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaRoute.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return RouteDto.class;
    }

    @Autowired
    RouteDayEntityDtoConverter<RouteDayDto, D, S> converter;

    @Autowired
    RouteService<R, D> service;

    @Override
    public DT toDto(R entity) {
        @SuppressWarnings("unchecked")
        DT dto = (DT) this.getModelMapperSupport().getModelMapper().map(entity, this.getDtoClass());
        dto.setDays(this.converter.toDtoList(this.service.getAllRouteDays(entity.getId(), null, null)));
        dto.setOwner(entity.getUser().getUsername());
        return dto;
    }
}
