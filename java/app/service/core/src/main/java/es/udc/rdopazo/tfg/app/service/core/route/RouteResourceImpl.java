package es.udc.rdopazo.tfg.app.service.core.route;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.service.core.route.converter.RouteEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.route.updater.RouteEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.route.RouteResource;
import es.udc.rdopazo.tfg.service.api.route.dto.RouteDto;

@Service
public class RouteResourceImpl<U extends Usuario, D extends RouteDay<S>, R extends Route<D, U>, S extends Stay<?, ?, ?>>
        implements RouteResource {

    @Autowired
    RouteService<R> rutaService;

    @Autowired
    RouteEntityDtoConverter<D, RouteDto, R, S> converter;

    @Autowired
    RouteEntityDtoUpdater<R> updater;

    public List<RouteDto> getAll(String filter, String value, String index, String count) {
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

        if (!(filter.equals("null")) && !(value.equals("null"))) {
            return this.converter.toDtoList(this.rutaService.getByField(filter, value, indexInt, countInt));
        }
        return this.converter.toDtoList(this.rutaService.getAll(indexInt, countInt));
    }

    public RouteDto getById(String id) throws InstanceNotFoundException {
        R ruta = null;
        try {
            ruta = this.rutaService.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.converter.toDto(ruta);
    }

    public RouteDto create(RouteDto rutaDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        U user = (U) authentication.getPrincipal();
        R ruta = this.converter.toEntity(rutaDto);
        ruta.setUser(user);
        R r = this.rutaService.add(ruta);
        return this.converter.toDto(r);
    }

    public RouteDto update(String id, RouteDto rutaDto) throws InstanceNotFoundException {
        R ruta = null;
        try {
            ruta = this.rutaService.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        ruta = this.updater.update(rutaDto, ruta);
        return this.converter.toDto(this.rutaService.update(ruta));
    }

    public void delete(String id) {
        Long idLong = null;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {

        }
        this.rutaService.delete(idLong);
    }

    public List<RouteDto> getByField(String filter, String value, String index, String count) {
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

        return this.converter.toDtoList(this.rutaService.getByField(filter, value, indexInt, countInt));
    }

}
