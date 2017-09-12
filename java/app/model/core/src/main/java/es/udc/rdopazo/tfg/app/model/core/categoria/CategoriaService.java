package es.udc.rdopazo.tfg.app.model.core.categoria;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;

public interface CategoriaService<C extends Categoria> {

    public List<C> getAll();

    public C getById(Long id);

    public C add(C categoria);

    public C update(C categoria);

    public void delete(Long id);
}
