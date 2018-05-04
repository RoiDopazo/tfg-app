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
import es.udc.rdopazo.tfg.app.service.core.util.InputValidator;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.route.RouteResource;
import es.udc.rdopazo.tfg.service.api.route.dto.RouteDto;

@Service
public class RouteResourceImpl<U extends Usuario, D extends RouteDay<S>, R extends Route<D, U>, S extends Stay<D, ?, ?>>
        implements RouteResource {

    private static final long serialVersionUID = 1L;

    @Autowired
    RouteService<R, D, S> rutaService;

    @Autowired
    RouteEntityDtoConverter<D, RouteDto, R, S> converter;

    @Autowired
    RouteEntityDtoUpdater<R, U> updater;

    public List<RouteDto> getAll(String filter, String value, String index, String count)
            throws InputValidationException {
        Integer indexInt = InputValidator.validateIntegerNull("index", index);
        Integer countInt = InputValidator.validateIntegerNull("count", count);

        return this.converter.toDtoList(this.rutaService.getRoutesByField(filter, value, indexInt, countInt));
    }

    public List<RouteDto> getOwnRoutes(String filter, String value, String index, String count)
            throws InputValidationException {
        Integer indexInt = InputValidator.validateIntegerNull("index", index);
        Integer countInt = InputValidator.validateIntegerNull("count", count);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        U user = (U) authentication.getPrincipal();

        return this.converter
                .toDtoList(this.rutaService.getRoutesByFields(user.getId(), filter, value, indexInt, countInt));
    }

    public RouteDto getById(String id) throws InstanceNotFoundException {
        R route = null;
        try {
            route = this.rutaService.getRouteById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this.converter.toDto(route);
    }

    public RouteDto create(RouteDto rutaDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        U user = (U) authentication.getPrincipal();
        R route = this.converter.toEntity(rutaDto);
        route.setUser(user);
        R r = this.rutaService.addRoute(route);
        return this.converter.toDto(r);
    }

    public RouteDto update(String id, RouteDto rutaDto) throws InstanceNotFoundException {
        R route = null;
        try {
            route = this.rutaService.getRouteById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        route = this.updater.update(rutaDto, route);
        return this.converter.toDto(this.rutaService.updateRoute(route));
    }

    public void delete(String id) throws InstanceNotFoundException {
        Long idLong = null;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {

        }
        this.rutaService.deleteRoute(idLong);
    }

    public List<RouteDto> getByField(String filter, String value, String index, String count)
            throws InputValidationException {
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

        return this.converter.toDtoList(this.rutaService.getRoutesByField(filter, value, indexInt, countInt));
    }

    public List<RouteDto> explore(String city, String state, String numDays, String maxDistance, String maxDuration,
            String index, String count) throws InputValidationException {
        Integer indexInt = InputValidator.validateIntegerNull("index", index);
        Integer countInt = InputValidator.validateIntegerNull("count", count);
        Long numDaysLong = InputValidator.validateLongNull("numDays", numDays);
        Long maxDistanceLong = InputValidator.validateLongNull("maxDistance", maxDistance);
        Long maxDurationLong = InputValidator.validateLongNull("maxDuration", maxDuration);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        U user = (U) authentication.getPrincipal();

        return this.converter.toDtoList(this.rutaService.exploreRoutes(user.getId(), city, state, numDaysLong,
                maxDistanceLong, maxDurationLong, indexInt, countInt));
    }

    public RouteDto updatePriv(String id, RouteDto routeDto)
            throws InstanceNotFoundException, InputValidationException {
        R route = null;
        Boolean privBool = InputValidator.validateBoolean("priv", routeDto.isPriv());
        try {
            route = this.rutaService.getRouteById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        route = this.updater.updatePriv(privBool, route);
        return this.converter.toDto(this.rutaService.updateRoute(route));
    }

}
