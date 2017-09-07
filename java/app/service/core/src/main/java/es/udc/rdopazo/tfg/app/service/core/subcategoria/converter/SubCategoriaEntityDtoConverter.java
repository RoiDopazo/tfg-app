package es.udc.rdopazo.tfg.app.service.core.subcategoria.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.subcategoria.SubCategoria;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.subcategoria.JpaSubCategoria;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.subcategoria.dto.SubCategoriaDto;

@Component
public class SubCategoriaEntityDtoConverter<D extends SubCategoriaDto, S extends SubCategoria<?>>
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
        return JpaSubCategoria.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return SubCategoriaDto.class;
    }
}
