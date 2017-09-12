package es.udc.rdopazo.tfg.app.service.core.ruta.rutalugar;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.ruta.rutalugar.RutaLugarService;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar.RutaLugar;
import es.udc.rdopazo.tfg.app.service.core.ruta.rutalugar.converter.RutaLugarEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.ruta.rutalugar.updater.RutaLugarEntityDtoUpdater;
import es.udc.rdopazo.tfg.service.api.ruta.RutaLugarResource;
import es.udc.rdopazo.tfg.service.api.ruta.dto.RutaLugarDto;

@Service
public class RutaLugarResourceImpl<RL extends RutaLugar<L>, L extends Lugar> implements RutaLugarResource {

    @Autowired
    RutaLugarService<RL> rutaLugarService;

    @Autowired
    RutaLugarEntityDtoConverter<RutaLugarDto, RL, L> converter;

    @Autowired
    RutaLugarEntityDtoUpdater updater;

    public List<RutaLugarDto> getAll(String id_ruta) {
        return this.converter.toDtoList(this.rutaLugarService.getAll());
    }

    @Transactional
    public RutaLugarDto create(String id_ruta, RutaLugarDto rutaLugarDto) {
        RL rutaLugar = this.converter.toEntity(rutaLugarDto, id_ruta);
        return this.converter.toDto(this.rutaLugarService.add(rutaLugar));
    }

}
