package es.udc.rdopazo.tfg.app.model.core.subcategoria.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.subcategoria.SubCategoriaService;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategoria.SubCategoria;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategoria.dao.SubCategoriaDao;

@Service
public class SubCategoriaServiceImpl<S extends SubCategoria<?>> implements SubCategoriaService<S> {

    @Autowired
    public SubCategoriaDao<S> dao;

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
