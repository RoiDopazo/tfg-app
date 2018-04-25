package es.udc.rdopazo.tfg.app.service.core.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.category.CategoryService;
import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.app.service.core.category.converter.CategoryEntityDtoConverter;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.category.CategoryResource;
import es.udc.rdopazo.tfg.service.api.category.dto.CategoryDto;

@Service
public class CategoryResourceImpl<C extends Category, S extends SubCategory<C>> implements CategoryResource {

    @Autowired
    public CategoryService<C, S> service;

    @Autowired
    public CategoryEntityDtoConverter<CategoryDto, C> converter;

    public List<CategoryDto> getAll() {
        return (this.converter.toDtoList(this.service.getAllCategories()));
    }

    public CategoryDto getById(String id) throws InstanceNotFoundException {
        C categoria = null;
        try {
            categoria = this.service.getCategoryById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.converter.toDto(categoria);
    }

}
