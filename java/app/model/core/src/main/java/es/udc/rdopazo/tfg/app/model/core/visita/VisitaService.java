package es.udc.rdopazo.tfg.app.model.core.visita;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.visita.Visita;

public interface VisitaService<V extends Visita<?, ?>> {

    List<V> getAll();

    V getById(Long id);

    V add(V visita);

    V update(V visita);

    void delete(Long id);
}
