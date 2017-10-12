package es.udc.rdopazo.tfg.app.service.core.dia.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.dia.JpaDia;
import es.udc.rdopazo.tfg.app.service.core.util.ModelMapperSupport;
import es.udc.rdopazo.tfg.service.api.dia.dto.DiaPersistDto;

@Component
public class DiaPersistDtoConverter<DT extends DiaPersistDto, D extends Dia> {

    @Autowired
    ModelMapperSupport modelMapper;

    protected Class<?> getEntityClass() {
        return JpaDia.class;
    }

    protected Class<?> getDtoClass() {
        return DiaPersistDto.class;
    }

    public D toEntity(DT dto) {
        @SuppressWarnings("unchecked")
        D entity = (D) this.modelMapper.getModelMapper().map(dto, this.getEntityClass());
        return entity;
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
