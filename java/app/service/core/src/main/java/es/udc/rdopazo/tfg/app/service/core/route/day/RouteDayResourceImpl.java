package es.udc.rdopazo.tfg.app.service.core.route.day;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.service.core.route.day.converter.RouteDayEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.route.day.updater.RouteDayEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.route.day.RouteDayResource;
import es.udc.rdopazo.tfg.service.api.route.day.dto.RealTimeDataDto;
import es.udc.rdopazo.tfg.service.api.route.day.dto.RouteDayDto;

@Service
public class RouteDayResourceImpl<R extends Route<D, ?>, D extends RouteDay<S>, S extends Stay<?, ?, ?>>
        implements RouteDayResource {

    @Autowired
    private RouteService<R, D> service;

    @Autowired
    private RouteDayEntityDtoConverter<RouteDayDto, D, S> converter;

    @Autowired
    private RouteDayEntityDtoUpdater<D> updater;

    public List<RouteDayDto> getAll(String idRoute, String index, String count) {

        Integer indexInt = null;
        Integer countInt = null;
        Long idRouteLong = null;

        try {
            indexInt = Integer.parseInt(index);
        } catch (NumberFormatException e) {
        }

        try {
            countInt = Integer.parseInt(count);
        } catch (NumberFormatException e) {
        }

        try {
            idRouteLong = Long.parseLong(idRoute);
        } catch (NumberFormatException e) {
        }

        return this.converter.toDtoList(this.service.getAllRouteDays(idRouteLong, indexInt, countInt));
    }

    @Transactional
    public RouteDayDto create(String idRoute) throws InstanceNotFoundException {
        R route = null;
        try {
            route = this.service.getRouteById(Long.parseLong(idRoute));
        } catch (NumberFormatException e) {

        }
        return this.converter.toDto(this.service.addRouteDay(route));
    }

    @Transactional
    public List<RouteDayDto> createNumDays(String idRoute, Integer numDays) throws InstanceNotFoundException {
        R route = null;
        try {
            route = this.service.getRouteById(Long.parseLong(idRoute));
        } catch (NumberFormatException e) {

        }
        return this.converter.toDtoList(this.service.createRouteDays(route, numDays));
    }

    @Transactional
    public void delete(String idRoute) throws InstanceNotFoundException {
        R route = null;
        Long routeId = null;
        try {
            routeId = Long.parseLong(idRoute);
            route = this.service.getRouteById(routeId);
        } catch (NumberFormatException e) {

        }
        if (route != null) {
            this.service.deleteRouteDay(routeId, (long) (route.getDays().size()));
        }

    }

    public RouteDayDto calculateHours(String idRoute, RouteDayDto diaDto) {
        // FIX -> hacer un validate de la ruta

        System.out.println(diaDto.getStartTime());
        for (int i = 0; i < diaDto.getStays().size(); i++) {
            if (i != 0) {
                diaDto.getStays().get(i - 1).getTime();
            }

        }

        return null;
    }

    public RouteDayDto update(String idRoute, String idDay, RouteDayDto diaDto) throws InstanceNotFoundException {
        D day = null;
        try {
            day = this.service.getRouteDayById(Long.parseLong(idRoute), Long.parseLong(idDay));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        day = this.updater.update(diaDto, day);
        return this.converter.toDto(this.service.updateRouteDay(day));
    }

    public void addRealTimeData(String idRoute, String idDay, RealTimeDataDto realTimeDataDto)
            throws InstanceNotFoundException {
        D day = null;
        try {
            day = this.service.getRouteDayById(Long.parseLong(idRoute), Long.parseLong(idDay));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        day = this.updater.updateRTD(realTimeDataDto, day);
        this.service.updateRouteDay(day);
    }

}
