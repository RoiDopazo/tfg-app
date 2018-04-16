package es.udc.rdopazo.tfg.app.service.core.category.converter;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.category.JpaCategory;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.category.dto.CategoryPersistDto;

@Component
public class CategoryPersistEntityDtoConverter<D extends CategoryPersistDto, C extends Category>
        extends DefaultEntityDtoConverterSupport<D, C> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaCategory.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return CategoryPersistDto.class;
    }

}
