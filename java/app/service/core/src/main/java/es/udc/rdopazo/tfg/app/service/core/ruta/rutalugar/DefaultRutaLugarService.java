package es.udc.rdopazo.tfg.app.service.core.ruta.rutalugar;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar.RutaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar.dao.RutaLugarDao;
import es.udc.rdopazo.tfg.app.service.core.ruta.rutalugar.converter.RutaLugarEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.ruta.rutalugar.updater.RutaLugarEntityDtoUpdater;
import es.udc.rdopazo.tfg.service.api.ruta.RutaLugarService;
import es.udc.rdopazo.tfg.service.api.ruta.dto.RutaLugarDto;

@Service
public class DefaultRutaLugarService<RL extends RutaLugar<L>, L extends Lugar> implements RutaLugarService {

    @Autowired
    RutaLugarDao<RL> dao;

    @Autowired
    RutaLugarEntityDtoConverter<RutaLugarDto, RL, L> converter;

    @Autowired
    RutaLugarEntityDtoUpdater updater;

    public List<RutaLugarDto> getAll(String id_ruta) {
        System.out.println("ID RUTA" + id_ruta);
        return this.converter.toDtoList(this.dao.getAll());
    }

    @Transactional
    public RutaLugarDto create(String id_ruta, RutaLugarDto rutaLugarDto) {
        RL rutaLugar = this.converter.toEntity(rutaLugarDto, id_ruta);
        this.dao.add(rutaLugar);
        return this.converter.toDto(rutaLugar);
    }

}
