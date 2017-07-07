package es.udc.rdopazo.tfg.app.model.core.categoria.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.api.categoria.dto.CategoriaDto;
import es.udc.rdopazo.tfg.app.model.core.util.ModelMapperSupport;
import es.udc.rdopazo.tfg.app.model.persistence.api.categoria.Categoria;

@Component
public class CategoriaEntityDtoConverter<C extends Categoria> {

    @Autowired
    ModelMapperSupport modelMapper;

    public CategoriaDto toCategoriaDto(C categoria) {
        CategoriaDto categoriaDto = this.modelMapper.getModelMapper().map(categoria, CategoriaDto.class);
        return categoriaDto;
    }

    public List<CategoriaDto> toCategoriaDtoList(List<C> categoria) {

        List<CategoriaDto> categoriaDtoList = new ArrayList<CategoriaDto>();
        for (C c : categoria) {
            categoriaDtoList.add(this.toCategoriaDto(c));
        }
        return categoriaDtoList;
    }

}
