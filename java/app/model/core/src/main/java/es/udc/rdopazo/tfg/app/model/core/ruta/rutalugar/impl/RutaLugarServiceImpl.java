package es.udc.rdopazo.tfg.app.model.core.ruta.rutalugar.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.ruta.rutalugar.RutaLugarService;
import es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar.RutaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar.dao.RutaLugarDao;

@Service
public class RutaLugarServiceImpl<RL extends RutaLugar<?>> implements RutaLugarService<RL> {

    @Autowired
    RutaLugarDao<RL> dao;

    public List<RL> getAll() {

        return this.dao.getAll();
    }

    public RL getById(Long id) {
        return this.dao.getById(id);
    }

    @Transactional
    public RL add(RL rutaLugar) {
        this.dao.add(rutaLugar);
        return rutaLugar;
    }

    @Transactional
    public RL update(RL rutaLugar) {
        this.dao.update(rutaLugar);
        return rutaLugar;
    }

    @Transactional
    public void delete(Long id) {
        this.dao.remove(this.dao.getById(id));
    }
}
