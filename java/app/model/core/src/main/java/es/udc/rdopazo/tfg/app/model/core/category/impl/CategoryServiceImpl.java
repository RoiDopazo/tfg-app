package es.udc.rdopazo.tfg.app.model.core.category.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.category.CategoryService;
import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.model.persistence.api.category.dao.CategoryDao;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

@Service
public class CategoryServiceImpl<C extends Category> implements CategoryService<C> {

    @Autowired
    public CategoryDao<C> dao;

    public List<C> getAll() {
        return this.dao.getAll();
    }

    public C getById(Long id) throws InstanceNotFoundException {
        C cat = this.dao.getById(id);
        if (cat == null) {
            throw new InstanceNotFoundException(id, "Category not found");
        } else {
            return cat;
        }
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
    public void delete(Long id) throws InstanceNotFoundException {
        this.dao.remove(this.getById(id));
    }

    public C getByField(String field, Object value) {
        return this.dao.getListByField(field, value).get(0);
    }

    public List<C> getListByField(String field, Object value, Integer index, Integer count) {
        if (!(field.equals("")) && !(value.equals(""))) {
            return this.dao.getListByField(field, value, index, count);
        } else {
            return this.dao.getAll(index, count);
        }
    }

    @Transactional
    public void clear() {
        this.dao.clear();
    }

}
