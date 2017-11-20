package es.udc.rdopazo.tfg.app.model.core.subcategory.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.subcategory.SubCategoryService;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.SubCategory;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategory.dao.SubCategoryDao;

@Service
public class SubCategoryServiceImpl<S extends SubCategory<?>> implements SubCategoryService<S> {

    @Autowired
    public SubCategoryDao<S> dao;

    @Override
    public List<S> getAll() {
        return this.dao.getAll();
    }

    @Override
    public S getById(Long id) {
        return this.dao.getById(id);
    }

    @Override
    @Transactional
    public S add(S subCategoria) {
        this.dao.add(subCategoria);
        return subCategoria;
    }

    @Override
    @Transactional
    public S update(S subCategoria) {
        this.dao.update(subCategoria);
        return subCategoria;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.dao.remove(this.dao.getById(id));
    }

}
