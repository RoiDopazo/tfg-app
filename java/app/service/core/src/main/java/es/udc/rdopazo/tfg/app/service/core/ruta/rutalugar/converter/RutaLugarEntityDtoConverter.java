package es.udc.rdopazo.tfg.app.service.core.ruta.rutalugar.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.dao.RutaDao;
import es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar.RutaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.rutalugar.JpaRutaLugar;
import es.udc.rdopazo.tfg.app.service.core.util.converter.DefaultEntityDtoConverterSupport;
import es.udc.rdopazo.tfg.service.api.ruta.dto.RutaLugarDto;

@Component
public class RutaLugarEntityDtoConverter<D extends RutaLugarDto, RL extends RutaLugar<L>, L extends Lugar>
        extends DefaultEntityDtoConverterSupport<D, RL> {

    @Autowired
    RutaDao<?> rutaDao;

    public RL toEntity(D dto, String id_ruta) {
        @SuppressWarnings("unchecked")
        RL entity = (RL) this.getModelMapperSupport().getModelMapper().map(dto, this.getEntityClass());
        entity.setRuta(this.rutaDao.getById(Long.parseLong(id_ruta)));
        return entity;
    }

    @Override
    protected Class<?> getEntityClass() {
        return JpaRutaLugar.class;
    }

    @Override
    protected Class<?> getDtoClass() {
        return RutaLugarDto.class;
    }

}
