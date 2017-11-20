package es.udc.rdopazo.tfg.app.model.core.category;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;

public interface CategoryService<C extends Category> {

    List<C> getAll();

    C getById(Long id);

    C add(C categoria);

    C update(C categoria);

    void delete(Long id);

    C getByField(String field, Object value);

}
