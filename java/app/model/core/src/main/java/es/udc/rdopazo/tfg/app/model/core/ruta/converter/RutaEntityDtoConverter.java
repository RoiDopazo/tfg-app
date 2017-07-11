package es.udc.rdopazo.tfg.app.model.core.ruta.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.api.ruta.dto.RutaDto;
import es.udc.rdopazo.tfg.app.api.rutalugar.dto.RutaLugarDto;
import es.udc.rdopazo.tfg.app.model.core.util.ModelMapperSupport;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar.RutaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.ruta.JpaRuta;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.rutalugar.JpaRutaLugar;

@Component
public class RutaEntityDtoConverter<R extends Ruta<RL>, RL extends RutaLugar<?>> {

    @Autowired
    ModelMapperSupport modelMapper;

    public R toRutaEntity(RutaDto rutaDto) {
        @SuppressWarnings("unchecked")
        R ruta = (R) this.modelMapper.getModelMapper().map(rutaDto, JpaRuta.class);
        List<RL> list = new ArrayList<RL>();
        for (RutaLugarDto rl : rutaDto.getRutaLugares()) {
            @SuppressWarnings("unchecked")
            RL rutaLugar = (RL) this.modelMapper.getModelMapper().map(rl, JpaRutaLugar.class);
            list.add(rutaLugar);

        }
        ruta.setRuta_lugares(list);
        return ruta;
    }

}
