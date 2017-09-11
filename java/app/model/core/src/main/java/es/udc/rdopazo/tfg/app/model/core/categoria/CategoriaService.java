package es.udc.rdopazo.tfg.app.model.core.categoria;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;

public interface CategoriaService {

    public List<Categoria> getAll();

    public Categoria getById(Long id);
}
