package es.udc.rdopazo.tfg.app.model.core.category.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.category.CategoryService;
import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.model.persistence.api.category.dao.CategoryDao;

@Service
public class CategoryServiceImpl<C extends Category> implements CategoryService<C> {

    @Autowired
    public CategoryDao<C> dao;

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
