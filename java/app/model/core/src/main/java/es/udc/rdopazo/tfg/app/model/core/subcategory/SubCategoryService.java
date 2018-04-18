package es.udc.rdopazo.tfg.app.model.core.subcategory;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public interface SubCategoryService<S extends SubCategory<?>> {

    public List<S> getAll();

    public S getById(Long id) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public S add(S subCategoria);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public S update(S subCategoria);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(Long id) throws InstanceNotFoundException;

    List<S> getByFields(Long idCategory, String filter, Object value, Integer index, Integer count);
}
