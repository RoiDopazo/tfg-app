package es.udc.rdopazo.tfg.app.service.core.route;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.service.core.route.converter.RouteEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.route.updater.RouteEntityDtoUpdater;
import es.udc.rdopazo.tfg.service.api.route.RouteResource;
import es.udc.rdopazo.tfg.service.api.route.dto.RouteDto;

@Service
public class RouteResourceImpl<D extends RouteDay<S>, R extends Route<D>, S extends Stay<?, ?, ?>>
        implements RouteResource {

    @Autowired
    RouteService<R> rutaService;

    @Autowired
    RouteEntityDtoConverter<D, RouteDto, R, S> converter;

    @Autowired
    RouteEntityDtoUpdater<R> updater;

    public List<RouteDto> getAll(String index, String count) {
        Integer indexInt = null;
        Integer countInt = null;

        try {
            indexInt = Integer.parseInt(index);
        } catch (NumberFormatException e) {
        }

        try {
            countInt = Integer.parseInt(count);
        } catch (NumberFormatException e) {
        }

        return this.converter.toDtoList(this.rutaService.getAll(indexInt, countInt));
    }

    public RouteDto getById(String id) {
        R ruta = null;
        try {
            ruta = this.rutaService.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.converter.toDto(ruta);
    }

    @Transactional
    public RouteDto create(RouteDto rutaDto) {
        R ruta = this.converter.toEntity(rutaDto);
        ruta.setNumDays(0);
        R r = this.rutaService.add(ruta);
        return this.converter.toDto(r);
    }

    @Transactional
    public RouteDto update(String id, RouteDto rutaDto) {
        R ruta = null;
        try {
            ruta = this.rutaService.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        ruta = this.updater.update(rutaDto, ruta);
        return this.converter.toDto(this.rutaService.update(ruta));
    }

    @Transactional
    public void delete(String id) {
        try {
            this.rutaService.delete(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
