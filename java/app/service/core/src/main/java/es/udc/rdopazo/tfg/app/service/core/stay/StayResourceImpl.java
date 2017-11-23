package es.udc.rdopazo.tfg.app.service.core.stay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.place.PlaceService;
import es.udc.rdopazo.tfg.app.model.core.route.day.RouteDayService;
import es.udc.rdopazo.tfg.app.model.core.stay.StayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.service.core.stay.converter.StayPlaceEntityDtoConverter;
import es.udc.rdopazo.tfg.service.api.stay.StayResource;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayPlaceDto;

@Service
public class StayResourceImpl<R extends Route<D>, D extends RouteDay<S>, P extends Place, S extends Stay<D, P>>
        implements StayResource {

    @Autowired
    private StayService<S> service;

    @Autowired
    private RouteDayService<R, D> diaService;

    @Autowired
    private PlaceService<P> placeService;

    @Autowired
    private StayPlaceEntityDtoConverter<StayPlaceDto, S> converterP;

    public List<StayDto> getAll(String idRoute, String idDay, String index, String count) {

        return null;
    }

    public List<StayDto> getById(String idStay) {
        Long idStayLong = null;
        try {
            idStayLong = Long.parseLong(idStay);
        } catch (NumberFormatException e) {

        }

        S stay = this.service.getById(idStayLong);
        return null;
    }

    public StayPlaceDto createP(String idRoute, String idDay, StayPlaceDto stayPlaceDto) {
        Long idRouteLong = null;
        Long idDayLong = null;

        try {
            idDayLong = Long.parseLong(idDay);
        } catch (NumberFormatException e) {
        }

        try {
            idRouteLong = Long.parseLong(idRoute);
        } catch (NumberFormatException e) {
        }

        D day = this.diaService.getById(idRouteLong, idDayLong);
        S entity = this.converterP.toEntity(stayPlaceDto);
        entity.setDay(day);
        entity.setOrder(this.service.getMaxOrderNum(idRouteLong, idDayLong));
        P place = this.placeService.getByField("idFoursquare", entity.getPlace().getIdFoursquare());
        if (place != null) {
            entity.getPlace().setId(place.getId());
        } else {
            P p = this.placeService.add(entity.getPlace());
            entity.setPlace(p);
        }
        return this.converterP.toDto(this.service.add(entity));
    }

}
