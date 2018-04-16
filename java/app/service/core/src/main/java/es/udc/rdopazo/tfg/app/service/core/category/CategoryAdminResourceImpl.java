package es.udc.rdopazo.tfg.app.service.core.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.category.CategoryService;
import es.udc.rdopazo.tfg.app.model.core.foursquare.FoursquareService;
import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.service.core.category.converter.CategoryPersistEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.category.updater.CategoryEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.InputValidator;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.category.CategoryAdminResource;
import es.udc.rdopazo.tfg.service.api.category.dto.CategoryPersistDto;

@Service
public class CategoryAdminResourceImpl<C extends Category> implements CategoryAdminResource {

    @Autowired
    private CategoryService<C> service;

    @Autowired
    private FoursquareService<C> fs_service;

    @Autowired
    private CategoryPersistEntityDtoConverter<CategoryPersistDto, C> converter;

    @Autowired
    private CategoryEntityDtoUpdater<C> updater;

    public List<CategoryPersistDto> getAll(String filter, String value, String index, String count)
            throws InputValidationException {
        Integer indexInt = InputValidator.validateIntegerNull("index", index);
        Integer countInt = InputValidator.validateIntegerNull("count", count);

        List<CategoryPersistDto> result = this.converter
                .toDtoList(this.service.getListByField(filter, value, indexInt, countInt));
        return result;
    }

    public CategoryPersistDto getById(String id) throws InstanceNotFoundException, InputValidationException {
        Long idCategory = InputValidator.validateLongNull("idCategory", id);
        C cat = this.service.getById(idCategory);
        return this.converter.toDto(cat);
    }

    public void load() {
        this.fs_service.getFoursquareCategories();

    }

    public CategoryPersistDto update(String id, CategoryPersistDto categoryPersistDto)
            throws InstanceNotFoundException, InputValidationException {
        Long idCategory = InputValidator.validateLongNull("idCategory", id);
        C cat = this.service.getById(idCategory);
        cat = this.updater.updatePersist(categoryPersistDto, cat);
        return this.converter.toDto(this.service.update(cat));
    }

    public void delete(String id) throws InputValidationException, InstanceNotFoundException {
        Long idCategory = InputValidator.validateLongNull("idCategory", id);
        this.service.delete(idCategory);

    }

}
