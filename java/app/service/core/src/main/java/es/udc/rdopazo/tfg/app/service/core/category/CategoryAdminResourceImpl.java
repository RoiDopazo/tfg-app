package es.udc.rdopazo.tfg.app.service.core.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.category.CategoryService;
import es.udc.rdopazo.tfg.app.model.core.externalservice.ExternalService;
import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.app.service.core.category.converter.CategoryPersistEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.category.updater.CategoryEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.InputValidator;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.category.CategoryAdminResource;
import es.udc.rdopazo.tfg.service.api.category.dto.CategoryPersistDto;

@Service
public class CategoryAdminResourceImpl<C extends Category, S extends SubCategory<C>> implements CategoryAdminResource {

    @Autowired
    private CategoryService<C, S> service;

    @Autowired
    private ExternalService<C> externalService;

    @Autowired
    private CategoryPersistEntityDtoConverter<CategoryPersistDto, C> converter;

    @Autowired
    private CategoryEntityDtoUpdater<C> updater;

    public List<CategoryPersistDto> getAll(String filter, String value, String index, String count)
            throws InputValidationException {
        Integer indexInt = InputValidator.validateIntegerNull("index", index);
        Integer countInt = InputValidator.validateIntegerNull("count", count);

        List<CategoryPersistDto> result = this.converter
                .toDtoList(this.service.getCategoriesListByField(filter, value, indexInt, countInt));
        return result;
    }

    public CategoryPersistDto getById(String id) throws InstanceNotFoundException, InputValidationException {
        Long idCategory = InputValidator.validateLongNull("idCategory", id);
        C cat = this.service.getCategoryById(idCategory);
        return this.converter.toDto(cat);
    }

    public void load() {
        this.externalService.getFSCategories();

    }

    public CategoryPersistDto update(String id, CategoryPersistDto categoryPersistDto)
            throws InstanceNotFoundException, InputValidationException {
        Long idCategory = InputValidator.validateLongNull("idCategory", id);
        C cat = this.service.getCategoryById(idCategory);
        cat = this.updater.updatePersist(categoryPersistDto, cat);
        return this.converter.toDto(this.service.updateCategory(cat));
    }

    public void delete(String id) throws InputValidationException, InstanceNotFoundException {
        Long idCategory = InputValidator.validateLongNull("idCategory", id);
        this.service.deleteCategory(idCategory);

    }

}
