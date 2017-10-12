package es.udc.rdopazo.tfg.app.model.persistence.api.dia.dao;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;

public interface DiaDao<D extends Dia> {

    void add(D entidad);

    void update(D entidad);

    void remove(D entidad);

    List<D> getAll(Long idRoute);

    List<D> getAll(Long idRoute, Integer index, Integer count);

    D getById(Long idRoute, Long idDay);

    List<D> getListByField(String fieldName, Object value);
}
