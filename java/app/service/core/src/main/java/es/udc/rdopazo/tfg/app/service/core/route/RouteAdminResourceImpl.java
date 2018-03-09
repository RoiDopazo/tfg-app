package es.udc.rdopazo.tfg.app.service.core.route;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.user.Role;
import es.udc.rdopazo.tfg.app.service.core.route.converter.RoutePersistEntityDtoConverter;
import es.udc.rdopazo.tfg.app.service.core.route.updater.RouteEntityDtoUpdater;
import es.udc.rdopazo.tfg.app.service.core.util.InputValidator;
import es.udc.rdopazo.tfg.app.util.exceptions.CustomErrorException;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.route.RouteAdminResource;
import es.udc.rdopazo.tfg.service.api.route.dto.RoutePersistDto;

@Service
public class RouteAdminResourceImpl<U extends Usuario, D extends RouteDay<?>, R extends Route<D, U>>
        implements RouteAdminResource {

    private static final long serialVersionUID = 1L;

    @Autowired
    private RouteService<R> service;

    @Autowired
    private RoutePersistEntityDtoConverter<D, RoutePersistDto, R> converter;

    @Autowired
    private RouteEntityDtoUpdater<R, U> updater;

    public List<RoutePersistDto> getAll(String filter, String value, String index, String count)
            throws InputValidationException {

        Integer indexInt = InputValidator.validateIntegerNull("index", index);
        Integer countInt = InputValidator.validateIntegerNull("count", count);

        List<RoutePersistDto> result = null;
        if (!(filter.equals("null")) && !(value.equals("null"))) {
            result = this.converter.toDtoList(this.service.getByField(filter, value, indexInt, countInt));
        } else {
            result = this.converter.toDtoList(this.service.getAll(indexInt, countInt));
        }
        return result;
    }

    public RoutePersistDto getById(String id) throws InstanceNotFoundException, InputValidationException {
        R ruta = null;
        try {
            ruta = this.service.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            throw new InputValidationException("Can not parse value of id: " + id);
        }
        return this.converter.toDto(ruta);
    }

    public RoutePersistDto create(RoutePersistDto routePersistDto) throws CustomErrorException {
        R ruta = this.converter.toEntity(routePersistDto);
        if (ruta.getUser() != null) {
            if (ruta.getUser().getRole() != Role.USER) {
                throw new CustomErrorException("Cannot not create route for user with not role 'USER'");
            }
        }
        R r = this.service.add(ruta);
        return this.converter.toDto(r);
    }

    public RoutePersistDto update(String id, RoutePersistDto routePersistDto)
            throws InstanceNotFoundException, InputValidationException, CustomErrorException {
        R ruta = null;
        try {
            ruta = this.service.getById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            throw new InputValidationException("Can not parse value of id: " + id);
        }
        ruta = this.updater.updatePersist(routePersistDto, ruta);
        return this.converter.toDto(this.service.update(ruta));
    }

    public void delete(String id) throws InstanceNotFoundException, InputValidationException {
        Long idLong = null;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new InputValidationException("Can not parse value of id: " + id);
        }
        this.service.delete(idLong);

    }

}
