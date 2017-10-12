package es.udc.rdopazo.tfg.app.service.core.ruta.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.service.api.ruta.dto.RutaDto;

@Component
public class RutaEntityDtoUpdater<R extends Ruta<?>> {

    public R update(RutaDto rutaDto, R ruta) {
        ruta.setName(rutaDto.getName());
        ruta.setCity(rutaDto.getCity());
        ruta.setDistance(rutaDto.getDistance());
        ruta.setState(rutaDto.getState());
        ruta.setNumPlaces(rutaDto.getNumPlaces());
        ruta.setTime(rutaDto.getTime());
        return ruta;
    }
}
