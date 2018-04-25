package es.udc.rdopazo.tfg.app.model.core.category.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.category.CategoryService;
import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import es.udc.rdopazo.tfg.app.model.persistence.api.category.dao.CategoryDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.dao.SubCategoryDao;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

@Service
public class CategoryServiceImpl<C extends Category, S extends SubCategory<C>> implements CategoryService<C, S> {

    @Autowired
    public CategoryDao<C> categoryDao;

    @Autowired
    public SubCategoryDao<S> subCategoryDao;

    public List<C> getAllCategories() {
        return this.categoryDao.getAll();
    }

    public C getCategoryById(Long id) throws InstanceNotFoundException {
        C cat = this.categoryDao.getById(id);
        if (cat == null) {
            throw new InstanceNotFoundException(id, "Category not found");
        } else {
            return cat;
        }
    }

    @Transactional
    public C addCategory(C categoria) {
        this.categoryDao.add(categoria);
        return categoria;
    }

    @Transactional
    public C updateCategory(C categoria) {
        this.categoryDao.update(categoria);
        return categoria;
    }

    @Transactional
    public void deleteCategory(Long id) throws InstanceNotFoundException {
        this.categoryDao.remove(this.getCategoryById(id));
    }

    public C getCategoryByField(String field, Object value) {
        return this.categoryDao.getListByField(field, value).get(0);
    }

    public List<C> getCategoriesListByField(String field, Object value, Integer index, Integer count) {
        if (!(field.equals("")) && !(value.equals(""))) {
            return this.categoryDao.getListByField(field, value, index, count);
        } else {
            return this.categoryDao.getAll(index, count);
        }
    }

    @Transactional
    public void clear() {
        this.categoryDao.clear();
    }

    public List<S> getAllSubCategories() {
        return this.subCategoryDao.getAll();
    }

    public S getSubCategoryById(Long id) throws InstanceNotFoundException {
        S scat = this.subCategoryDao.getById(id);
        if (scat == null) {
            throw new InstanceNotFoundException(id, "SubCategory not found");
        } else {
            return scat;
        }
    }

    @Transactional
    public S addSubCategory(S subCategoria) {
        this.subCategoryDao.add(subCategoria);
        return subCategoria;
    }

    @Transactional
    public S updateSubCategory(S subCategoria) {
        this.subCategoryDao.update(subCategoria);
        return subCategoria;
    }

    @Transactional
    public void deleteSubCategory(Long id) throws InstanceNotFoundException {
        this.subCategoryDao.remove(this.getSubCategoryById(id));

    }

    public List<S> getSubCategoriesByFields(Long category, String field, Object value, Integer index, Integer count) {
        Map<String, Object> fields = new HashMap<String, Object>();
        if (category != null) {
            fields.put("category-id", category);
        }

        if (!(field.equals("")) && !(value.equals(""))) {
            fields.put(field, value);
        }
        return this.subCategoryDao.getListByFields(fields, index, count);
    }

}
