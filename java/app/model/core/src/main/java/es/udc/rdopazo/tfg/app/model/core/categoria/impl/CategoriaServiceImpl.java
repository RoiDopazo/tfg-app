package es.udc.rdopazo.tfg.app.model.core.categoria.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.categoria.CategoriaService;
import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;
import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.dao.CategoriaDao;

@Service
public class CategoriaServiceImpl<C extends Categoria> implements CategoriaService<C> {

    @Autowired
    public CategoriaDao<C> dao;

    public List<C> getAll() {
        return this.dao.getAll();
    }

    public C getById(Long id) {
        return this.dao.getById(id);
    }

    @Transactional
    public C add(C categoria) {
        this.dao.add(categoria);
        return categoria;
    }

    @Transactional
    public C update(C categoria) {
        this.dao.update(categoria);
        return categoria;
    }

    @Transactional
    public void delete(Long id) {
        this.dao.remove(this.dao.getById(id));
    }

    public C getByField(String field, Object value) {
        return this.dao.getListByField(field, value).get(0);
    }

}
