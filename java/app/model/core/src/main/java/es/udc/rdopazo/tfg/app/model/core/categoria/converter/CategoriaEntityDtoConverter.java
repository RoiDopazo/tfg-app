package es.udc.rdopazo.tfg.app.model.core.categoria.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.api.categoria.dto.CategoriaDto;
import es.udc.rdopazo.tfg.app.model.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;

@Component
public class CategoriaEntityDtoConverter<D extends CategoriaDto, C extends Categoria>
        extends DefaultEntityDtoConverterSupport<D, C> {

    @Override
    protected Class<?> getEntityClass() {
        return Categoria.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return CategoriaDto.class;
    }

}
