package es.udc.rdopazo.tfg.app.model.core.comentario.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.comentario.ComentarioService;
import es.udc.rdopazo.tfg.app.model.persistence.api.comentario.Comentario;
import es.udc.rdopazo.tfg.app.model.persistence.api.comentario.dao.ComentarioDao;

@Service
public class ComentarioServiceImpl<C extends Comentario> implements ComentarioService<C> {

    @Autowired
    ComentarioDao<C> dao;

    public List<C> getAll() {

        return this.dao.getAll();
    }

    public C getById(Long id) {
        return this.dao.getById(id);
    }

    @Transactional
    public C add(C comentario) {
        this.dao.add(comentario);
        return comentario;
    }

    @Transactional
    public C update(C comentario) {
        this.dao.update(comentario);
        return comentario;
    }

    @Transactional
    public void delete(Long id) {
        this.dao.remove(this.dao.getById(id));
    }
}
