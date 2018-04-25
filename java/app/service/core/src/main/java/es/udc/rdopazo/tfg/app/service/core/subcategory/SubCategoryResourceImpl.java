package es.udc.rdopazo.tfg.app.service.core.subcategory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.category.CategoryService;
import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.app.service.core.subcategory.converter.SubCategoryEntityDtoConverter;
import es.udc.rdopazo.tfg.service.api.subcategory.SubCategoryResource;
import es.udc.rdopazo.tfg.service.api.subcategory.dto.SubCategoryDto;

@Service
public class SubCategoryResourceImpl<C extends Category, S extends SubCategory<C>> implements SubCategoryResource {

    @Autowired
    public CategoryService<C, S> service;

    @Autowired
    public SubCategoryEntityDtoConverter<SubCategoryDto, S> converter;

    public List<SubCategoryDto> getAll() {
        return (this.converter.toDtoList(this.service.getAllSubCategories()));
    }

    public SubCategoryDto getById(String id) {
        S subCategoria = null;
        try {
            subCategoria = this.service.getSubCategoryById(Long.parseLong(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.converter.toDto(subCategoria);
    }

}
