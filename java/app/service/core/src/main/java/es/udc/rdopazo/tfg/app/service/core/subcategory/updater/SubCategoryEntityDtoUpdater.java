package es.udc.rdopazo.tfg.app.service.core.subcategory.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.service.api.subcategory.dto.SubCategoryPersistDto;

@Component
public class SubCategoryEntityDtoUpdater<S extends SubCategory<?>> {

    public S updatePersist(SubCategoryPersistDto dto, S entity) {
        entity.setIconPrefix(dto.getIconPrefix());
        entity.setIconSuffix(dto.getIconSuffix());
        entity.setName(dto.getName());
        entity.setPluralName(dto.getPluralName());
        return entity;
    }

}
