package es.udc.rdopazo.tfg.app.service.core.ruta.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.service.api.ruta.dto.RutaDto;

@Component
public class RutaEntityDtoUpdater<R extends Ruta<?>> {

    public R update(RutaDto rutaDto, R ruta) {
        ruta.setCiudad(rutaDto.getCiudad());
        ruta.setDistancia(rutaDto.getDistancia());
        ruta.setEstado(rutaDto.getEstado());
        ruta.setNumero_lugares(rutaDto.getNumero_lugares());
        ruta.setTiempo(rutaDto.getTiempo());
        return ruta;
    }
}
