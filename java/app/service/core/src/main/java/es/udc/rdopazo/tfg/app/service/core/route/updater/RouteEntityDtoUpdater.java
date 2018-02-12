package es.udc.rdopazo.tfg.app.service.core.route.updater;

import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.service.api.route.dto.RouteDto;

@Component
public class RouteEntityDtoUpdater<R extends Route<?, ?>> {

    public R update(RouteDto rutaDto, R ruta) {
        ruta.setName(rutaDto.getName());
        ruta.setCity(rutaDto.getCity());
        ruta.setCountry(rutaDto.getCountry());
        ruta.setPhoto(rutaDto.getPhoto());
        ruta.setStartDate(rutaDto.getStartDate());
        ruta.setEndDate(rutaDto.getEndDate());
        return ruta;
    }
}
