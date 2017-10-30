package es.udc.rdopazo.tfg.app.service.core.ruta.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.core.dia.DiaService;
import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.ruta.JpaRuta;
import es.udc.rdopazo.tfg.app.service.core.dia.converter.DiaEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.dia.dto.DiaDto;
import es.udc.rdopazo.tfg.service.api.ruta.dto.RutaDto;

@Component
public class RutaEntityDtoConverter<D extends Dia, DT extends RutaDto, R extends Ruta<D>>
        extends DefaultEntityDtoConverterSupport<DT, R> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaRuta.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return RutaDto.class;
    }

    @Autowired
    DiaEntityDtoConverter<DiaDto, D> diaConverter;

    @Autowired
    DiaService<R, D> diaService;

    @Override
    public DT toDto(R entity) {
        @SuppressWarnings("unchecked")
        DT dto = (DT) this.getModelMapperSupport().getModelMapper().map(entity, this.getDtoClass());
        dto.setDays(this.diaConverter.toDtoList(this.diaService.getAll(entity.getId(), null, null)));

        return dto;
    }
}
