package es.udc.rdopazo.tfg.app.model.core.ruta.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.api.ruta.dto.RutaDto;
import es.udc.rdopazo.tfg.app.model.core.util.ModelMapperSupport;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.ruta.JpaRuta;

@Component
public class RutaEntityDtoConverter<R extends Ruta<?>> {

    @Autowired
    ModelMapperSupport modelMapper;

    public R toRutaEntity(RutaDto rutaDto) {
        @SuppressWarnings("unchecked")
        R ruta = (R) this.modelMapper.getModelMapper().map(rutaDto, JpaRuta.class);
        return ruta;
    }

    public RutaDto toRutaDto(R ruta) {
        RutaDto rutaDto = this.modelMapper.getModelMapper().map(ruta, RutaDto.class);
        return rutaDto;
    }

    public List<RutaDto> tuRutaDtoList(List<R> rutas) {
        List<RutaDto> rutaDtoList = new ArrayList<RutaDto>();
        for (R ruta : rutas) {
            rutaDtoList.add(this.toRutaDto(ruta));
        }
        return rutaDtoList;
    }
}
