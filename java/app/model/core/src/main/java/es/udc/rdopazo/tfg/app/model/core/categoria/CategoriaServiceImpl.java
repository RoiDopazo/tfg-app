package es.udc.rdopazo.tfg.app.model.core.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;
import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.dao.CategoriaDao;

@Service
public class CategoriaServiceImpl<C extends Categoria> {

    @Autowired
    public CategoriaDao<C> dao;

    public List<C> getAll() {
        return this.dao.getAll();
    }

    public Categoria getById(Long id) {
        return this.dao.getById(id);
    }

}
