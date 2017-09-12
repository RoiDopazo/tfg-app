package es.udc.rdopazo.tfg.app.service.core.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.categoria.CategoriaService;
import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;
import es.udc.rdopazo.tfg.app.service.core.categoria.converter.CategoriaEntityDtoConverter;
import es.udc.rdopazo.tfg.service.api.categoria.CategoriaResource;
import es.udc.rdopazo.tfg.service.api.categoria.dto.CategoriaDto;

@Service
public class CategoriaResourceImpl<C extends Categoria> implements CategoriaResource {

    @Autowired
    public CategoriaService<C> categoriaService;

    @Autowired
    public CategoriaEntityDtoConverter<CategoriaDto, C> converter;

    public List<CategoriaDto> getAll() {
        return (this.converter.toDtoList(this.categoriaService.getAll()));
    }

    public CategoriaDto getById(String id) {
        C categoria = null;
        try {
            categoria = this.categoriaService.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.converter.toDto(categoria);
    }

}
