package es.udc.rdopazo.tfg.app.service.core.stay.updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.core.event.EventService;
import es.udc.rdopazo.tfg.app.model.core.place.PlaceService;
import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayPersistDto;

@Component
public class StayEntityDtoUpdater<S extends Stay<RD, P, EP>, R extends Route<RD, ?>, RD extends RouteDay<S>, P extends Place, EP extends EventPlace<?>> {

    @Autowired
    private RouteService<R, RD> routeService;

    @Autowired
    private PlaceService<P> placeService;

    @Autowired
    private EventService<?, ?, EP> eventService;

    public S update(StayDto stayDto, S stay) {
        stay.setOrder(stayDto.getOrder());
        stay.setTime(stayDto.getTime());
        stay.setTravelDistance(stayDto.getTravelDistance());
        stay.setTravelMode(stayDto.getTravelMode());
        stay.setTravelTime(stayDto.getTravelTime());
        return stay;
    }

    public S updatePersist(StayPersistDto stayPersistDto, S stay) throws InstanceNotFoundException {
        stay.setOrder(stayPersistDto.getOrder());
        stay.setTime(stayPersistDto.getTime());
        stay.setTravelDistance(stayPersistDto.getTravelDistance());
        stay.setTravelMode(stayPersistDto.getTravelMode());
        stay.setTravelTime(stayPersistDto.getTravelTime());
        stay.setDay(this.routeService.getRouteDayById(stayPersistDto.getIdRoute(), stayPersistDto.getIdDay()));
        if ((stayPersistDto.getIdPlace() != null) && (stayPersistDto.getType().equals("PL"))) {
            if (stay.getType().equals("PL")) {
                stay.setPlace(this.placeService.getById(stayPersistDto.getIdPlace()));
                stay.setEventPlace(null);
            }

        }
        if ((stayPersistDto.getIdEventPlace() != null) && (stayPersistDto.getType().equals("EP"))) {
            if (stay.getType().equals("EP")) {
                stay.setEventPlace(this.eventService.getEventPlaceById(stayPersistDto.getIdEventPlace()));
                stay.setPlace(null);
            }
        }
        return stay;

    }
}
