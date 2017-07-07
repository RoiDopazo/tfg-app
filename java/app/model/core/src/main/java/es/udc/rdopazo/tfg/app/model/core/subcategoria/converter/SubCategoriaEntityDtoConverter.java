package es.udc.rdopazo.tfg.app.model.core.subcategoria.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.api.categoria.dto.CategoriaDto;
import es.udc.rdopazo.tfg.app.api.subcategoria.dto.SubCategoriaDto;
import es.udc.rdopazo.tfg.app.model.core.util.ModelMapperSupport;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategoria.SubCategoria;

@Component
public class SubCategoriaEntityDtoConverter<S extends SubCategoria<?>> {

    @Autowired
    ModelMapperSupport modelMapper;

    public SubCategoriaDto toSubCategoriaDto(S subCategoria) {

        SubCategoriaDto subCategoriaDto = this.modelMapper.getModelMapper().map(subCategoria, SubCategoriaDto.class);
        subCategoriaDto.setId_categoria(subCategoria.getCategoria().getId());
        subCategoriaDto.setCategoriaDto(
                this.modelMapper.getModelMapper().map(subCategoria.getCategoria(), CategoriaDto.class));
        return subCategoriaDto;
    }

    public List<SubCategoriaDto> toSubCategoriaDtoList(List<S> subCategoria) {

        List<SubCategoriaDto> subCategoriaDtoList = new ArrayList<SubCategoriaDto>();
        for (S s : subCategoria) {
            subCategoriaDtoList.add(this.toSubCategoriaDto(s));
        }
        return subCategoriaDtoList;
    }
}
