package es.udc.rdopazo.tfg.app.service.core.stay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.place.PlaceService;
import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.service.core.stay.converter.StayEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.stay.updater.StayEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.InputValidator;
import es.udc.rdopazo.tfg.app.util.enums.Role;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.UnUpdateableRouteException;
import es.udc.rdopazo.tfg.service.api.stay.StayResource;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayConfListDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayEventPlaceDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayPlaceDto;

@Service
public class StayResourceImpl<R extends Route<D, ?>, D extends RouteDay<S>, P extends Place, EP extends EventPlace<?>, S extends Stay<D, P, EP>>
        implements StayResource {

    @Autowired
    private RouteService<R, D, S> service;

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

        return this.converter.toDtoList(this.service.getAllStaysInDay(idRouteLong, idDayLong));
    }

    public StayDto getById(String idStay) throws InstanceNotFoundException {
        Long idStayLong = null;
        try {
            idStayLong = Long.parseLong(idStay);
        } catch (NumberFormatException e) {

        }

        return this.converter.toDto(this.service.getStayById(idStayLong));
    }

    public StayDto createByPlace(String idRoute, String idDay, StayPlaceDto stayPlaceDto)
            throws InstanceNotFoundException, UnUpdateableRouteException {
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

        D day = this.service.getRouteDayById(idRouteLong, idDayLong);
        S stay = this.converter.toEntityP(stayPlaceDto);
        stay.setEventPlace(null);
        stay.setDay(day);
        if (this.checkSecurity(stay.getDay().getRoute().getUser().getUsername())) {
            throw new AccessDeniedException("Acceso denegado");
        }
        stay.setOrder(this.service.getStayMaxOrderNum(idRouteLong, idDayLong));
        List<P> places = this.placeService.getListByField("idFoursquare", stay.getPlace().getIdFoursquare(), null,
                null);
        P place = null;
        if (places.size() > 0) {
            place = places.get(0);
        }
        if (place != null) {
            stay.getPlace().setId(place.getId());
        } else {
            P p = this.placeService.add(stay.getPlace());
            stay.setPlace(p);
        }
        return this.converter.toDto(this.service.addStay(stay));
    }

    public StayDto createByEventPlace(String idRoute, String idDay, StayEventPlaceDto stayEventPlaceDto)
            throws InstanceNotFoundException, UnUpdateableRouteException {
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

        D day = this.service.getRouteDayById(idRouteLong, idDayLong);
        S entity = this.converter.toEntityE(stayEventPlaceDto);
        entity.setPlace(null);
        entity.setDay(day);
        if (this.checkSecurity(entity.getDay().getRoute().getUser().getUsername())) {
            throw new AccessDeniedException("Acceso denegado");
        }
        entity.setOrder(this.service.getStayMaxOrderNum(idRouteLong, idDayLong));
        if (entity.getEventPlace() == null) {
            return null;
        } else {
            entity = this.service.addStay(entity);
            return this.converter.toDto(entity);
        }
    }

    public void delete(String idStay) throws InstanceNotFoundException, UnUpdateableRouteException {
        Long idStayLong = null;
        try {
            idStayLong = Long.parseLong(idStay);
        } catch (NumberFormatException e) {

        }
        S stayEntity = this.service.getStayById(idStayLong);
        this.service.deleteStay(idStayLong);
        this.service.fixStaysOrdersAfterDelete(stayEntity.getDay().getDiaPK().getIdRoute(),
                stayEntity.getDay().getDiaPK().getIdDay());
    }

    public Boolean createAndDeleteBatch(String idRoute, StayConfListDto stayConfDto)
            throws InstanceNotFoundException, UnUpdateableRouteException {

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
            List<P> places = this.placeService.getListByField("idFoursquare",
                    stayConfDto.getStay().getPlace().getIdFoursquare(), null, null);
            P place = null;
            if (places.size() > 0) {
                place = places.get(0);
            }
            if (place != null) {
                List<S> stays = this.service.getStaysByRouteDayAndPlace(idRouteLong, idDay, place.getId());
                for (S s : stays) {
                    this.delete(s.getId().toString());
                }
            }
            this.service.fixStaysOrdersAfterDelete(idRouteLong, idDay);
        }

        // Lugares a añadir en el dia
        for (Long idDay : stayConfDto.getDaysAfter()) {
            this.createByPlace(idRoute, idDay.toString(), stayConfDto.getStay());
        }
        return true;
    }

    public List<StayDto> updateBatch(List<StayDto> stayListDto)
            throws InstanceNotFoundException, UnUpdateableRouteException {

        List<StayDto> returnList = new ArrayList<StayDto>();
        for (StayDto stayDto : stayListDto) {
            S stayPlace = this.service.getStayById(stayDto.getId());
            stayPlace = this.updater.update(stayDto, stayPlace);
            if (this.checkSecurity(stayPlace.getDay().getRoute().getUser().getUsername())) {
                returnList.add(this.converter.toDto(this.service.updateStay(stayPlace)));
            } else {
                throw new AccessDeniedException("Acceso denegado");
            }
        }
        return returnList;
    }

    public StayDto update(String idStay, StayDto stay)
            throws InstanceNotFoundException, UnUpdateableRouteException, InputValidationException {
        Long idStayLong = InputValidator.validateLongNull("idStay", idStay);
        S stayPlace = this.service.getStayById(idStayLong);
        stayPlace = this.updater.update(stay, stayPlace);
        if (this.checkSecurity(stayPlace.getDay().getRoute().getUser().getUsername())) {
            return this.converter.toDto(this.service.updateStay(stayPlace));
        } else {
            throw new AccessDeniedException("Acceso denegado");
        }
    }

    private boolean checkSecurity(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario user = (Usuario) authentication.getPrincipal();
        if (user.getRole() == Role.ADMIN) {
            return true;
        } else {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;

    }
}
