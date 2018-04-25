package es.udc.rdopazo.tfg.app.service.core.subcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.category.CategoryService;
import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.app.service.core.subcategory.converter.SubCategoryPersistEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.subcategory.updater.SubCategoryEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.InputValidator;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.subcategory.SubCategoryAdminResource;
import es.udc.rdopazo.tfg.service.api.subcategory.dto.SubCategoryPersistDto;

@Service
public class SubCategoryAdminResourceImpl<C extends Category, S extends SubCategory<C>>
        implements SubCategoryAdminResource {

    @Autowired
    private CategoryService<C, S> service;

    @Autowired
    private SubCategoryPersistEntityDtoConverter<SubCategoryPersistDto, S> converter;

    @Autowired
    private SubCategoryEntityDtoUpdater<S> updater;

    public List<SubCategoryPersistDto> getAll(String category, String filter, String value, String index, String count)
            throws InputValidationException {
        Long idCategory = InputValidator.validateLongNull("idCategory", category);
        Integer indexInt = InputValidator.validateIntegerNull("index", index);
        Integer countInt = InputValidator.validateIntegerNull("count", count);

        List<SubCategoryPersistDto> result = this.converter
                .toDtoList(this.service.getSubCategoriesByFields(idCategory, filter, value, indexInt, countInt));
        return result;
    }

    public SubCategoryPersistDto getById(String id) throws InstanceNotFoundException, InputValidationException {
        Long idSubCategory = InputValidator.validateLongNull("idSubCategory", id);
        S scat = this.service.getSubCategoryById(idSubCategory);
        return this.converter.toDto(scat);
    }

    public SubCategoryPersistDto update(String id, SubCategoryPersistDto subCategoryPersistDto)
            throws InstanceNotFoundException, InputValidationException {
        Long idSubCategory = InputValidator.validateLongNull("idSubCategory", id);
        S scat = this.service.getSubCategoryById(idSubCategory);
        scat = this.updater.updatePersist(subCategoryPersistDto, scat);
        return this.converter.toDto(this.service.updateSubCategory(scat));
    }

    public void delete(String id) throws InputValidationException, InstanceNotFoundException {
        Long idSubCategory = InputValidator.validateLongNull("idSubCategory", id);
        this.service.deleteSubCategory(idSubCategory);

    }

}
