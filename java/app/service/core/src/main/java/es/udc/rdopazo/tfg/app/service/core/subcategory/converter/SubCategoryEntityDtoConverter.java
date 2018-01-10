package es.udc.rdopazo.tfg.app.service.core.subcategory.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.subcategory.JpaSubCategory;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.subcategory.dto.SubCategoryDto;

@Component
public class SubCategoryEntityDtoConverter<D extends SubCategoryDto, S extends SubCategory<?>>
        extends DefaultEntityDtoConverterSupport<D, S> {

    @Override
    public D toDto(S subCategoria) {
        @SuppressWarnings("unchecked")
        D subCategoriaDto = (D) this.getModelMapperSupport().getModelMapper().map(subCategoria, this.getEntityClass());
        subCategoriaDto.setId_categoria(subCategoria.getCategoria().getId());
        return null;

    }

    @Override
    protected Class<?> getEntityClass() {
        return JpaSubCategory.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return SubCategoryDto.class;
    }
}