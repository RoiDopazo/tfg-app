package es.udc.rdopazo.tfg.app.model.core.dia;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;

public interface DiaService<R extends Ruta<D>, D extends Dia> {

    public D add(R rotue, D day);

    public D update(D day);

    public void delete(Long idRoute, Long idDay);

    public List<D> getAll(Long idRoute, Integer index, Integer count);

    public D getById(Long idRoute, Long idDay);

}
