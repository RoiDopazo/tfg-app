package es.udc.rdopazo.tfg.app.service.core.category.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.category.dto.CategoryDto;
import es.udc.rdopazo.tfg.service.api.util.EntityDto;

@Component
public class CategoryEntityDtoConverter<D extends EntityDto, C extends Category>
        extends DefaultEntityDtoConverterSupport<D, C> {

    @Override
    protected Class<?> getEntityClass() {
        return Category.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return CategoryDto.class;
    }

}
