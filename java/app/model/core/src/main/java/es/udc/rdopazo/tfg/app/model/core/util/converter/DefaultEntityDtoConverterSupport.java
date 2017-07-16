package es.udc.rdopazo.tfg.app.model.core.util.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.rdopazo.tfg.app.api.util.EntityDto;
import es.udc.rdopazo.tfg.app.model.core.util.ModelMapperSupport;
import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;

public abstract class DefaultEntityDtoConverterSupport<D extends EntityDto, E extends Entity<Long>> {

    @Autowired
    ModelMapperSupport modelMapper;

    protected abstract Class<?> getEntityClass();

    protected abstract Class<?> getDtoClass();

    public E toEntity(D dto) {
        @SuppressWarnings("unchecked")
        E entity = (E) this.modelMapper.getModelMapper().map(dto, this.getEntityClass());
        return entity;
    }

    public D toDto(E entity) {
        @SuppressWarnings("unchecked")
        D dto = (D) this.modelMapper.getModelMapper().map(entity, this.getDtoClass());
        return dto;
    }

    public List<D> toDtoList(List<E> entities) {
        List<D> dtoList = new ArrayList<D>();
        for (E entity : entities) {
            dtoList.add(this.toDto(entity));
        }
        return dtoList;
    }

    public List<E> toEntityList(List<D> dtos) {
        List<E> entityList = new ArrayList<E>();
        for (D dto : dtos) {
            entityList.add(this.toEntity(dto));
        }
        return entityList;
    }

    public ModelMapperSupport getModelMapperSupport() {
        return this.modelMapper;
    }
}
