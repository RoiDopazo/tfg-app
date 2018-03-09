package es.udc.rdopazo.tfg.app.service.core.route.day;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.core.route.day.RouteDayService;
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
    private RouteService<R> rutaService;

    @Autowired
    private RouteDayService<R, D> diaService;

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

        return this.converter.toDtoList(this.diaService.getAll(idRouteLong, indexInt, countInt));
    }

    @Transactional
    public RouteDayDto create(String idRoute) throws InstanceNotFoundException {
        R route = null;
        try {
            route = this.rutaService.getById(Long.parseLong(idRoute));
        } catch (NumberFormatException e) {

        }
        return this.converter.toDto(this.diaService.add(route));
    }

    @Transactional
    public List<RouteDayDto> createNumDays(String idRoute, Integer numDays) throws InstanceNotFoundException {
        R route = null;
        try {
            route = this.rutaService.getById(Long.parseLong(idRoute));
        } catch (NumberFormatException e) {

        }
        return this.converter.toDtoList(this.diaService.createDays(route, numDays));
    }

    @Transactional
    public void delete(String idRoute) throws InstanceNotFoundException {
        R route = null;
        Long routeId = null;
        try {
            routeId = Long.parseLong(idRoute);
            route = this.rutaService.getById(routeId);
        } catch (NumberFormatException e) {

        }
        if (route != null) {
            this.diaService.delete(routeId, (long) (route.getDays().size()));
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
            day = this.diaService.getById(Long.parseLong(idRoute), Long.parseLong(idDay));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        day = this.updater.update(diaDto, day);
        return this.converter.toDto(this.diaService.update(day));
    }

    public void addRealTimeData(String idRoute, String idDay, RealTimeDataDto realTimeDataDto)
            throws InstanceNotFoundException {
        D day = null;
        try {
            day = this.diaService.getById(Long.parseLong(idRoute), Long.parseLong(idDay));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        day = this.updater.updateRTD(realTimeDataDto, day);
        this.diaService.update(day);
    }

}
