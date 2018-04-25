package es.udc.rdopazo.tfg.app.service.core.stay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.core.stay.StayService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.service.core.stay.converter.StayPersistEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.stay.updater.StayEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.InputValidator;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.stay.StayAdminResource;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayPersistDto;

@Service
public class StayAdminResourceImpl<R extends Route<D, ?>, D extends RouteDay<S>, P extends Place, EP extends EventPlace<?>, S extends Stay<D, P, EP>>
        implements StayAdminResource {

    private static final long serialVersionUID = 1L;

    @Autowired
    private RouteService<R, D> routeService;

    @Autowired
    private StayService<S, D, P, EP> service;

    @Autowired
    private StayPersistEntityDtoConverter<S, StayPersistDto> converter;

    @Autowired
    private StayEntityDtoUpdater<S, R, D, P, EP> updater;

    public List<StayPersistDto> getAll(String route, String day, String filter, String value, String index,
            String count) throws InputValidationException {
        Integer indexInt = InputValidator.validateIntegerNull("index", index);
        Integer countInt = InputValidator.validateIntegerNull("count", count);
        Long idRoute = InputValidator.validateLongNull("idRoute", route);
        Long idDay = InputValidator.validateLongNull("idDay", day);

        List<StayPersistDto> result = this.converter
                .toDtoList(this.service.getByFields(idRoute, idDay, filter, value, indexInt, countInt));
        return result;

    }

    public StayPersistDto getById(String id) throws InstanceNotFoundException, InputValidationException {
        Long idStay = InputValidator.validateLongNull("idStay", id);
        S stay = this.service.getById(idStay);
        return this.converter.toDto(stay);
    }

    public StayPersistDto create(StayPersistDto stayPersistDto)
            throws InstanceNotFoundException, InputValidationException {

        InputValidator.validateStayPlaceType("type", stayPersistDto.getType());
        S stay = null;
        if (stayPersistDto.getType().equals("PL")) {
            stay = this.converter.toEntityP(stayPersistDto);
        }
        if (stayPersistDto.getType().equals("EP")) {
            stay = this.converter.toEntityE(stayPersistDto);
        }
        stay.setDay(this.routeService.getRouteDayById(stayPersistDto.getIdRoute(), stayPersistDto.getIdDay()));
        stay = this.service.add(stay);
        return this.converter.toDto(stay);
    }

    public StayPersistDto update(String id, StayPersistDto stayPersistDto)
            throws InstanceNotFoundException, InputValidationException {
        InputValidator.validateStayPlaceType("type", stayPersistDto.getType());
        Long idStay = InputValidator.validateLongNull("idStay", id);
        S stay = this.service.getById(idStay);
        stay = this.updater.updatePersist(stayPersistDto, stay);
        return this.converter.toDto(this.service.update(stay));
    }

    public void delete(String id) throws InputValidationException, InstanceNotFoundException {
        Long idStay = InputValidator.validateLongNull("idStay", id);
        this.service.delete(idStay);
    }

}
