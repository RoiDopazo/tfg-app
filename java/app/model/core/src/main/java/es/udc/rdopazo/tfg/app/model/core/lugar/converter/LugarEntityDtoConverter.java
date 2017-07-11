package es.udc.rdopazo.tfg.app.model.core.lugar.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.api.lugar.dto.LugarDto;
import es.udc.rdopazo.tfg.app.model.core.util.ModelMapperSupport;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.lugar.JpaLugar;

@Component
public class LugarEntityDtoConverter<L extends Lugar> {

    @Autowired
    ModelMapperSupport modelMapper;

    public L toLugarEntity(LugarDto lugarDto) {
        @SuppressWarnings("unchecked")
        L lugar = (L) this.modelMapper.getModelMapper().map(lugarDto, JpaLugar.class);
        return lugar;
    }
}
