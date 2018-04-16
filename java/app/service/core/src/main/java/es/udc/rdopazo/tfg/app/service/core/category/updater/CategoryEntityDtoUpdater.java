package es.udc.rdopazo.tfg.app.service.core.category.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.service.api.category.dto.CategoryPersistDto;

@Component
public class CategoryEntityDtoUpdater<C extends Category> {

    public C updatePersist(CategoryPersistDto dto, C entity) {
        entity.setName(dto.getName());
        entity.setIconPrefix(dto.getIconPrefix());
        entity.setIconSuffix(dto.getIconSuffix());
        entity.setPluralName(dto.getPluralName());
        return entity;
    }

}
