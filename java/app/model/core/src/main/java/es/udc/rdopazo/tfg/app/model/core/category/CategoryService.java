package es.udc.rdopazo.tfg.app.model.core.category;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

public interface CategoryService<C extends Category> {

    List<C> getAll();

    C getById(Long id) throws InstanceNotFoundException;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    C add(C categoria);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    C update(C categoria);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Long id) throws InstanceNotFoundException;

    C getByField(String field, Object value);

    List<C> getListByField(String field, Object value, Integer index, Integer count);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void clear();
}
