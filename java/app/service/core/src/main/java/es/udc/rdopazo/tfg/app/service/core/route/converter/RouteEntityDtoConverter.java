package es.udc.rdopazo.tfg.app.service.core.route.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.core.route.day.RouteDayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.JpaRoute;
import es.udc.rdopazo.tfg.app.service.core.route.day.converter.RouteDayEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.route.day.dto.RouteDayDto;
import es.udc.rdopazo.tfg.service.api.route.dto.RouteDto;

@Component
public class RouteEntityDtoConverter<D extends RouteDay, DT extends RouteDto, R extends Route<D>>
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
    RouteDayEntityDtoConverter<RouteDayDto, D> diaConverter;

    @Autowired
    RouteDayService<R, D> diaService;

    @Override
    public DT toDto(R entity) {
        @SuppressWarnings("unchecked")
        DT dto = (DT) this.getModelMapperSupport().getModelMapper().map(entity, this.getDtoClass());
        dto.setDays(this.diaConverter.toDtoList(this.diaService.getAll(entity.getId(), null, null)));

        return dto;
    }
}
