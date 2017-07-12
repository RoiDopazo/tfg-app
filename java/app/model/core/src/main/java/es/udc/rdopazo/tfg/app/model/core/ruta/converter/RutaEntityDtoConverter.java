package es.udc.rdopazo.tfg.app.model.core.ruta.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.api.ruta.dto.RutaDto;
import es.udc.rdopazo.tfg.app.model.core.rutalugar.converter.RutaLugarEntityDtoConverter;
import es.udc.rdopazo.tfg.app.model.core.util.ModelMapperSupport;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar.RutaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.ruta.JpaRuta;

@Component
public class RutaEntityDtoConverter<R extends Ruta<RL>, RL extends RutaLugar<?>> {

    @Autowired
    ModelMapperSupport modelMapper;

    @Autowired
    RutaLugarEntityDtoConverter<RL, R> rutaLugarConverter;

    public R toRutaEntity(RutaDto rutaDto) {
        @SuppressWarnings("unchecked")
        R ruta = (R) this.modelMapper.getModelMapper().map(rutaDto, JpaRuta.class);
        return ruta;
    }

}
