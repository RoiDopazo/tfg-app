package es.udc.rdopazo.tfg.app.model.core.categoria;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;

public interface CategoriaService<C extends Categoria> {

    List<C> getAll();

    C getById(Long id);

    C add(C categoria);

    C update(C categoria);

    void delete(Long id);

    C getByField(String field, Object value);

}
