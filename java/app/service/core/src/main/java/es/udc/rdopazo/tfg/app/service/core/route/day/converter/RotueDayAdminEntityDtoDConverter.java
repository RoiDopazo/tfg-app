package es.udc.rdopazo.tfg.app.service.core.route.day.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.JpaRouteDay;
import es.udc.rdopazo.tfg.app.service.core.util.ModelMapperSupport;
import es.udc.rdopazo.tfg.service.api.route.day.dto.RouteDayPersistDto;

@Component
public class RotueDayAdminEntityDtoDConverter<DT extends RouteDayPersistDto, D extends RouteDay<?>> {

    @Autowired
    ModelMapperSupport modelMapper;

    protected Class<?> getEntityClass() {
        return JpaRouteDay.class;
    }

    protected Class<?> getDtoClass() {
        return RouteDayPersistDto.class;
    }

    public D toEntity(DT dto) {
        @SuppressWarnings("unchecked")
        D entity = (D) this.modelMapper.getModelMapper().map(dto, this.getEntityClass());
        return entity;
    }

    public DT toDto(D entity) {
        @SuppressWarnings("unchecked")
        DT dto = (DT) this.modelMapper.getModelMapper().map(entity, this.getDtoClass());
        dto.setIdRoute(entity.getDiaPK().getIdRoute());
        dto.setIdDay(entity.getDiaPK().getIdDay());
        return dto;
    }

    public List<DT> toDtoList(List<D> entities) {
        List<DT> dtoList = new ArrayList<DT>();
        for (D entity : entities) {
            dtoList.add(this.toDto(entity));
        }
        return dtoList;
    }

    public List<D> toEntityList(List<DT> dtos) {
        List<D> entityList = new ArrayList<D>();
        for (DT dto : dtos) {
            entityList.add(this.toEntity(dto));
        }
        return entityList;
    }

    public ModelMapperSupport getModelMapperSupport() {
        return this.modelMapper;
    }
}
