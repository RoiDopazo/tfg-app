package es.udc.rdopazo.tfg.app.service.core.subcategoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.subcategoria.SubCategoriaService;
import es.udc.rdopazo.tfg.app.model.persistence.api.subcategoria.SubCategoria;
import es.udc.rdopazo.tfg.app.service.core.subcategoria.converter.SubCategoriaEntityDtoConverter;
import es.udc.rdopazo.tfg.service.api.subcategoria.SubCategoriaResource;
import es.udc.rdopazo.tfg.service.api.subcategoria.dto.SubCategoriaDto;

@Service
public class SubCategoriaResourceImpl<S extends SubCategoria<?>> implements SubCategoriaResource {

    @Autowired
    public SubCategoriaService<S> subCategoriaService;

    @Autowired
    public SubCategoriaEntityDtoConverter<SubCategoriaDto, S> converter;

    public List<SubCategoriaDto> getAll() {
        return (this.converter.toDtoList(this.subCategoriaService.getAll()));
    }

    public SubCategoriaDto getById(String id) {
        S subCategoria = null;
        try {
            subCategoria = this.subCategoriaService.getById(Long.parseLong(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.converter.toDto(subCategoria);
    }

}
