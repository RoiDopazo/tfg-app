package es.udc.rdopazo.tfg.app.model.core.category;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public interface CategoryService<C extends Category, S extends SubCategory<C>> {

    List<C> getAllCategories();

    C getCategoryById(Long id) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    C addCategory(C categoria);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    C updateCategory(C categoria);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteCategory(Long id) throws InstanceNotFoundException;

    List<C> getCategoriesListByField(String field, Object value, Integer index, Integer count);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void clear();

    List<S> getAllSubCategories();

    S getSubCategoryById(Long id) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    S addSubCategory(S subCategoria);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    S updateSubCategory(S subCategoria);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteSubCategory(Long id) throws InstanceNotFoundException;

    List<S> getSubCategoriesByFields(Long idCategory, String filter, Object value, Integer index, Integer count);
}
