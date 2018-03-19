package es.udc.rdopazo.tfg.app.service.core.stay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.place.PlaceService;
import es.udc.rdopazo.tfg.app.model.core.route.day.RouteDayService;
import es.udc.rdopazo.tfg.app.model.core.stay.StayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.service.core.stay.converter.StayEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.stay.updater.StayEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.stay.StayResource;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayConfListDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayEventPlaceDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayPlaceDto;

@Service
public class StayResourceImpl<R extends Route<D, ?>, D extends RouteDay<S>, P extends Place, EP extends EventPlace<?>, S extends Stay<D, P, EP>>
        implements StayResource {

    @Autowired
    private StayService<S> service;

    @Autowired
    private RouteDayService<R, D> diaService;

    @Autowired
    private PlaceService<P> placeService;

    @Autowired
    private StayEntityDtoConverter<StayDto, S> converter;

    @Autowired
    private StayEntityDtoUpdater<S, R, D, P, EP> updater;

    public List<StayDto> getAll(String idRoute, String idDay, String index, String count) {

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

        return this.converter.toDtoList(this.service.getAllInDay(idRouteLong, idDayLong));
    }

    public StayDto getById(String idStay) throws InstanceNotFoundException {
        Long idStayLong = null;
        try {
            idStayLong = Long.parseLong(idStay);
        } catch (NumberFormatException e) {

        }

        return this.converter.toDto(this.service.getById(idStayLong));
    }

    public StayDto createByPlace(String idRoute, String idDay, StayPlaceDto stayPlaceDto)
            throws InstanceNotFoundException {
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
        S entity = this.converter.toEntityP(stayPlaceDto);
        entity.setEventPlace(null);
        entity.setDay(day);
        entity.setOrder(this.service.getMaxOrderNum(idRouteLong, idDayLong));
        P place = this.placeService.getByField("idFoursquare", entity.getPlace().getIdFoursquare());
        if (place != null) {
            entity.getPlace().setId(place.getId());
        } else {
            P p = this.placeService.add(entity.getPlace());
            entity.setPlace(p);
        }
        return this.converter.toDto(this.service.add(entity));
    }

    public StayDto createByEventPlace(String idRoute, String idDay, StayEventPlaceDto stayEventPlaceDto)
            throws InstanceNotFoundException {
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
        S entity = this.converter.toEntityE(stayEventPlaceDto);
        entity.setPlace(null);
        entity.setDay(day);
        entity.setOrder(this.service.getMaxOrderNum(idRouteLong, idDayLong));
        if (entity.getEventPlace() == null) {
            return null;
        } else {
            entity = this.service.add(entity);
            return this.converter.toDto(entity);
        }
    }

    public void delete(String idStay) throws InstanceNotFoundException {
        Long idStayLong = null;
        try {
            idStayLong = Long.parseLong(idStay);
        } catch (NumberFormatException e) {

        }
        S stayEntity = this.service.getById(idStayLong);
        this.service.delete(idStayLong);
        this.service.fixOrdersAfterDelete(stayEntity.getDay().getDiaPK().getIdRoute(),
                stayEntity.getDay().getDiaPK().getIdDay());
    }

    public Boolean createAndDeleteBatch(String idRoute, StayConfListDto stayConfDto) throws InstanceNotFoundException {

        Long idRouteLong = null;
        Long idDayLong = null;

        try {
            idRouteLong = Long.parseLong(idRoute);
        } catch (NumberFormatException e) {
        }

        Set<Long> intersect = new HashSet<Long>(stayConfDto.getDaysBefore());
        intersect.retainAll(stayConfDto.getDaysAfter());
        stayConfDto.getDaysBefore().removeAll(intersect);
        stayConfDto.getDaysAfter().removeAll(intersect);

        // Lugares a eliminar del día
        for (Long idDay : stayConfDto.getDaysBefore()) {
            P place = this.placeService.getByField("idFoursquare", stayConfDto.getStay().getPlace().getIdFoursquare());
            if (place != null) {
                List<S> stays = this.service.getByRouteAndDayAndPlace(idRouteLong, idDay, place.getId());
                for (S s : stays) {
                    this.delete(s.getId().toString());
                }
            }
            this.service.fixOrdersAfterDelete(idRouteLong, idDay);
        }

        // Lugares a añadir en el dia
        for (Long idDay : stayConfDto.getDaysAfter()) {
            this.createByPlace(idRoute, idDay.toString(), stayConfDto.getStay());
        }
        return true;
    }

    public List<StayDto> updateBatch(List<StayDto> stayListDto) throws InstanceNotFoundException {

        List<StayDto> returnList = new ArrayList<StayDto>();
        for (StayDto stayDto : stayListDto) {
            S stayPlace = this.service.getById(stayDto.getId());
            stayPlace = this.updater.update(stayDto, stayPlace);
            returnList.add(this.converter.toDto(this.service.update(stayPlace)));
        }
        return returnList;
    }
}
