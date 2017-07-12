package es.udc.rdopazo.tfg.app.model.core.rutalugar.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.api.rutalugar.dto.RutaLugarDto;
import es.udc.rdopazo.tfg.app.model.core.util.ModelMapperSupport;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.dao.RutaDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar.RutaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.rutalugar.JpaRutaLugar;

@Component
public class RutaLugarEntityDtoConverter<RL extends RutaLugar<?>, R extends Ruta<?>> {

    @Autowired
    ModelMapperSupport modelMapper;

    @Autowired
    RutaDao<R> rutaDao;

    public RL toRutaLugarEntity(RutaLugarDto rutaLugarDto) {
        @SuppressWarnings("unchecked")
        RL rutaLugar = (RL) this.modelMapper.getModelMapper().map(rutaLugarDto, JpaRutaLugar.class);

        rutaLugar.setRuta(this.rutaDao.getById(100L));
        return rutaLugar;
    }
}
