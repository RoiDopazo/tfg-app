package es.udc.rdopazo.tfg.app.model.core.ruta.rutalugar;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar.RutaLugar;

public interface RutaLugarService<RL extends RutaLugar<?>> {

    public List<RL> getAll();

    public RL getById(Long id);

    public RL add(RL rutaLugar);

    public RL update(RL rutaLugar);

    public void delete(Long id);
}
