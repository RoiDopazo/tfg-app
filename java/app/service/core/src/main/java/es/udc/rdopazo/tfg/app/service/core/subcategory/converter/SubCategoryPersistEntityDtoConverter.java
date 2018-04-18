package es.udc.rdopazo.tfg.app.service.core.subcategory.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.subcategory.JpaSubCategory;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.subcategory.dto.SubCategoryPersistDto;

@Component
public class SubCategoryPersistEntityDtoConverter<D extends SubCategoryPersistDto, S extends SubCategory<?>>
        extends DefaultEntityDtoConverterSupport<D, S> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaSubCategory.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return SubCategoryPersistDto.class;
    }

    @Override
    public D toDto(S entity) {
        @SuppressWarnings("unchecked")
        D dto = (D) this.getModelMapperSupport().getModelMapper().map(entity, this.getDtoClass());
        dto.setIdCategory(entity.getCategory().getId());
        return dto;
    }
}
