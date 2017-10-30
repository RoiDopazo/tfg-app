package es.udc.rdopazo.tfg.app.service.core.ruta.converter;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.dia.JpaDia;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.ruta.JpaRuta;
import es.udc.rdopazo.tfg.app.service.core.dia.converter.DiaEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.dia.dto.DiaDto;
import es.udc.rdopazo.tfg.service.api.ruta.dto.RutaDto;

@Component
public class RutaEntityDtoConverter<D extends RutaDto, R extends Ruta<?>>
        extends DefaultEntityDtoConverterSupport<D, R> {

    @Override
    protected Class<?> getEntityClass() {
        return JpaRuta.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return RutaDto.class;
    }

    @Autowired
    DiaEntityDtoConverter<DiaDto, JpaDia> diaConverter;

    @Override
    public D toDto(R entity) {
        @SuppressWarnings("unchecked")
        D dto = (D) this.getModelMapperSupport().getModelMapper().map(entity, this.getDtoClass());
        if (entity.getDays() == null) {
        	dto.setDays(new ArrayList());
        } else {
        	 dto.setDays(this.diaConverter.toDtoList(entity.getDays()));
        }
       
        return dto;
    }
}
