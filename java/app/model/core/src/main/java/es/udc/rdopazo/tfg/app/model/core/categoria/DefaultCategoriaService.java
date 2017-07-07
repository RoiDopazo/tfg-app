package es.udc.rdopazo.tfg.app.model.core.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.api.categoria.CategoriaDto;
import es.udc.rdopazo.tfg.app.api.categoria.CategoriaService;
import es.udc.rdopazo.tfg.app.model.core.categoria.converter.CategoriaEntityDtoConverter;
import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;
import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.dao.CategoriaDao;

@Service
public class DefaultCategoriaService<C extends Categoria> implements CategoriaService {

    @Autowired
    public CategoriaDao<C> categoriaDao;

    @Autowired
    public CategoriaEntityDtoConverter<C> converter;

    public List<CategoriaDto> getAll() {
        List<C> categoriaList = this.categoriaDao.getAll();
        return (this.converter.toCategoriaDtoList(categoriaList));
    }

    public CategoriaDto getById(String id) {
        C categoria = null;
        try {
            categoria = this.categoriaDao.getById(Long.parseLong(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.converter.toCategoriaDto(categoria);
    }

}
