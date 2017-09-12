package es.udc.rdopazo.tfg.app.model.core.subcategoria;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.subcategoria.SubCategoria;

public interface SubCategoriaService<S extends SubCategoria<?>> {

    public List<S> getAll();

    public S getById(Long id);

    public S add(S subCategoria);

    public S update(S subCategoria);

    public void delete(Long id);
}
