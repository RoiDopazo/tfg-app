package es.udc.rdopazo.tfg.app.model.core.ruta;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;

public interface RutaService<R extends Ruta<?>> {

    List<R> getAll(Integer index, Integer count);

    R getById(Long id);

    R add(R ruta);

    R update(R ruta);

    void delete(Long id);
}
