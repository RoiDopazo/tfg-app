package es.udc.rdopazo.tfg.app.service.core.categoria.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.categoria.dto.CategoriaDto;

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
