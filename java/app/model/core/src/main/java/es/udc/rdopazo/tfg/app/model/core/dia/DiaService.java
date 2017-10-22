package es.udc.rdopazo.tfg.app.model.core.dia;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;

public interface DiaService<R extends Ruta<D>, D extends Dia> {

    D add(R rotue, D day);

    List<D> createDays(R route, Integer numDays);

    D update(D day);

    void delete(Long idRoute, Long idDay);

    List<D> getAll(Long idRoute, Integer index, Integer count);

    D getById(Long idRoute, Long idDay);

    List<Long> getListDaysByRotueAndPlace(Long idRoute, String idFoursquare);

}
