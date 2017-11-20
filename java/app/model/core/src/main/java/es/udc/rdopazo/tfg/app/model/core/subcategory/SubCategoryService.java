package es.udc.rdopazo.tfg.app.model.core.subcategory;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;

public interface SubCategoryService<S extends SubCategory<?>> {

    public List<S> getAll();

    public S getById(Long id);

    public S add(S subCategoria);

    public S update(S subCategoria);

    public void delete(Long id);
}
