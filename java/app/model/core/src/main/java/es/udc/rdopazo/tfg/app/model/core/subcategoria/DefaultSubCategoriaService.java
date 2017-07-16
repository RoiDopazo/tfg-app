package es.udc.rdopazo.tfg.app.model.core.subcategoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.api.subcategoria.SubCategoriaService;
import es.udc.rdopazo.tfg.app.api.subcategoria.dto.SubCategoriaDto;
import es.udc.rdopazo.tfg.app.model.core.subcategoria.converter.SubCategoriaEntityDtoConverter;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategoria.SubCategoria;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategoria.dao.SubCategoriaDao;

@Service
public class DefaultSubCategoriaService<S extends SubCategoria<?>> implements SubCategoriaService {

    @Autowired
    public SubCategoriaDao<S> dao;

    @Autowired
    public SubCategoriaEntityDtoConverter<SubCategoriaDto, S> converter;

    public List<SubCategoriaDto> getAll() {
        return (this.converter.toDtoList(this.dao.getAll()));
    }

    public SubCategoriaDto getById(String id) {
        S subCategoria = null;
        try {
            subCategoria = this.dao.getById(Long.parseLong(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.converter.toDto(subCategoria);
    }

}
