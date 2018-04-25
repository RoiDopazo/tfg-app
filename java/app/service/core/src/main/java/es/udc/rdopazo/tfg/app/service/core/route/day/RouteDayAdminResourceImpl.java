package es.udc.rdopazo.tfg.app.service.core.route.day;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.service.core.route.day.converter.RotueDayAdminEntityDtoDConverter;
import es.udc.rdopazo.tfg.app.service.core.route.day.updater.RouteDayEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.InputValidator;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.route.day.RouteDayAdminResource;
import es.udc.rdopazo.tfg.service.api.route.day.dto.RouteDayPersistDto;

@Service
public class RouteDayAdminResourceImpl<R extends Route<D, ?>, D extends RouteDay<?>> implements RouteDayAdminResource {

    @Autowired
    private RouteService<R, D> service;

    @Autowired
    private RotueDayAdminEntityDtoDConverter<RouteDayPersistDto, D> converter;

    @Autowired
    private RouteDayEntityDtoUpdater<D> updater;

    public List<RouteDayPersistDto> getAll(String route, String filter, String value, String index, String count)
            throws InputValidationException {

        Long idRoute = InputValidator.validateLongNull("idRoute", route);
        Integer indexInt = InputValidator.validateIntegerNull("index", index);
        Integer countInt = InputValidator.validateIntegerNull("count", count);

        List<RouteDayPersistDto> result = this.converter
                .toDtoList(this.service.getRouteDaysByFields(idRoute, filter, value, indexInt, countInt));
        return result;
    }

    public RouteDayPersistDto getById(String idRoute, String idDay)
            throws InputValidationException, InstanceNotFoundException {

        Long idRouteLong = InputValidator.validateLongNull("idRoute", idRoute);
        Long idDayLong = InputValidator.validateLongNull("idDay", idDay);

        D routeDay = this.service.getRouteDayById(idRouteLong, idDayLong);
        return this.converter.toDto(routeDay);
    }

    public RouteDayPersistDto update(String idRoute, String idDay, RouteDayPersistDto diaDto)
            throws InputValidationException, InstanceNotFoundException {

        Long idRouteLong = InputValidator.validateLongNull("idRoute", idRoute);
        Long idDayLong = InputValidator.validateLongNull("idDay", idDay);

        D routeDay = this.service.getRouteDayById(idRouteLong, idDayLong);
        routeDay = this.updater.updatePersist(diaDto, routeDay);
        return this.converter.toDto(this.service.updateRouteDay(routeDay));
    }

    public RouteDayPersistDto create(String idRoute, RouteDayPersistDto routeDayPersistDto)
            throws InstanceNotFoundException, InputValidationException {

        Long idRouteLong = InputValidator.validateLongNull("idRoute", idRoute);
        R route = this.service.getRouteById(idRouteLong);

        D rd = this.service.addRouteDay(route, routeDayPersistDto.getStartTime(), routeDayPersistDto.getRealTimeData());
        return this.converter.toDto(rd);
    }

    public void delete(String idRoute) throws InstanceNotFoundException, InputValidationException {
        Long idRouteLong = InputValidator.validateLongNull("idRoute", idRoute);
        R route = this.service.getRouteById(idRouteLong);

        this.service.deleteRouteDay(idRouteLong, (long) route.getDays().size());
    }

    public List<RouteDayPersistDto> createNumDays(String idRoute, Integer numDays)
            throws InstanceNotFoundException, InputValidationException {
        Long idRouteLong = InputValidator.validateLongNull("idRoute", idRoute);
        R route = this.service.getRouteById(idRouteLong);
        return this.converter.toDtoList(this.service.createRouteDays(route, numDays));
    }

}
