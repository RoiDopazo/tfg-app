package es.udc.rdopazo.tfg.app.model.core.ruta;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;

public interface RutaService<R extends Ruta<?>> {

    public List<R> getAll();

    public R getById(Long id);

    public R add(R ruta);

    public R update(R ruta);

    public void delete(Long id);
}
