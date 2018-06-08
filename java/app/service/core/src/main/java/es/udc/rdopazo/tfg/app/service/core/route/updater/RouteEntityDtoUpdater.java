package es.udc.rdopazo.tfg.app.service.core.route.updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.core.usuario.UsuarioService;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.util.enums.Role;
import es.udc.rdopazo.tfg.app.util.exceptions.CustomErrorException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.route.dto.RouteDto;
import es.udc.rdopazo.tfg.service.api.route.dto.RoutePersistDto;

@Component
public class RouteEntityDtoUpdater<R extends Route<?, U>, U extends Usuario> {

    @Autowired
    private UsuarioService<U> userService;

    public R update(RouteDto routeDto, R route) {
        route.setCity(routeDto.getCity());
        route.setCountry(routeDto.getCountry());
        route.setPhoto(routeDto.getPhoto());
        route.setStartDate(routeDto.getStartDate());
        route.setEndDate(routeDto.getEndDate());
        route.setLat(routeDto.getLat());
        route.setLng(routeDto.getLng());
        route.setPriv(routeDto.isPriv());

        return route;
    }

    public R updatePriv(Boolean priv, R route) {
        route.setPriv(priv);

        return route;
    }

    public R updatePersist(RoutePersistDto routeDto, R route) throws CustomErrorException, InstanceNotFoundException {
        route.setCity(routeDto.getCity());
        route.setCountry(routeDto.getCountry());
        route.setPhoto(routeDto.getPhoto());
        route.setStartDate(routeDto.getStartDate());
        route.setEndDate(routeDto.getEndDate());
        route.setLat(routeDto.getLat());
        route.setLng(routeDto.getLng());
        route.setPriv(routeDto.isPriv());
        if (routeDto.getUserId() != route.getUser().getId()) {
            U user = this.userService.getById(routeDto.getUserId());
            if (user.getRole() != Role.USER) {
                throw new CustomErrorException("Can not assign route to a Admin User");
            }
            route.setUser(user);
        }
        return route;
    }
}
