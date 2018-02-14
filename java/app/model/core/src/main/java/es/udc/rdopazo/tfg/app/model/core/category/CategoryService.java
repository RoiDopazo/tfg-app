package es.udc.rdopazo.tfg.app.model.core.category;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;

public interface CategoryService<C extends Category> {

    List<C> getAll();

    C getById(Long id);

    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    C add(C categoria);

    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    C update(C categoria);

    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    void delete(Long id);

    C getByField(String field, Object value);

}
