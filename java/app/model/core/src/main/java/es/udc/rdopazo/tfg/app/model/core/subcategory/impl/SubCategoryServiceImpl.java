package es.udc.rdopazo.tfg.app.model.core.subcategory.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.subcategory.SubCategoryService;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.dao.SubCategoryDao;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

@Service
public class SubCategoryServiceImpl<S extends SubCategory<?>> implements SubCategoryService<S> {

    @Autowired
    public SubCategoryDao<S> dao;

    public List<S> getAll() {
        return this.dao.getAll();
    }

    public S getById(Long id) throws InstanceNotFoundException {
        S scat = this.dao.getById(id);
        if (scat == null) {
            throw new InstanceNotFoundException(id, "SubCategory not found");
        } else {
            return scat;
        }
    }

    @Transactional
    public S add(S subCategoria) {
        this.dao.add(subCategoria);
        return subCategoria;
    }

    @Transactional
    public S update(S subCategoria) {
        this.dao.update(subCategoria);
        return subCategoria;
    }

    @Transactional
    public void delete(Long id) throws InstanceNotFoundException {
        this.dao.remove(this.getById(id));

    }

    public List<S> getByFields(Long category, String field, Object value, Integer index, Integer count) {
        Map<String, Object> fields = new HashMap<String, Object>();
        if (category != null) {
            fields.put("category-id", category);
        }

        if (!(field.equals("")) && !(value.equals(""))) {
            fields.put(field, value);
        }
        return this.dao.getListByFields(fields, index, count);
    }

}
