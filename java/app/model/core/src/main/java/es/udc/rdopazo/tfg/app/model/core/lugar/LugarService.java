package es.udc.rdopazo.tfg.app.model.core.lugar;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;

public interface LugarService<L extends Lugar> {

    public List<L> getAll();

    public L getByField(String fieldName, Object value);

    public L getById(Long id);

    public L add(L lugar);

    public L update(L lugar);

    public void delete(Long id);
}
