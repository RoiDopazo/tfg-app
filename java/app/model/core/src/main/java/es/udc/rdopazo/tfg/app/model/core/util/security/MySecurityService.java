package es.udc.rdopazo.tfg.app.model.core.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.core.usuario.UsuarioService;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;

@Component("mySecurityService")
public class MySecurityService<U extends Usuario, R extends Route<RD, U>, RD extends RouteDay<?>, S extends Stay<RD, P, EP>, P extends Place, EP extends EventPlace<?>> {

    @Autowired
    private UsuarioService<U> userService;

    @Autowired
    private RouteService<R, RD, S> routeService;

    public boolean hasUserPermission(Authentication authentication, Long id) {

        U usuario = null;
        try {
            usuario = this.userService.getById(id);
        } catch (InstanceNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        U user = (U) authentication.getPrincipal();
        if (usuario.getUsername().equals(user.getUsername())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasRoutePermission(Authentication authentication, Long id) {

        R route = null;
        try {
            route = this.routeService.getRouteById(id);
        } catch (InstanceNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        U user = (U) authentication.getPrincipal();
        if (route.getUser().getUsername().equals(user.getUsername())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasRouteDayPermission(Authentication authentication, Long idRoute, Long idDay) {

        RD routeDay = null;
        try {
            routeDay = this.routeService.getRouteDayById(idRoute, idDay);
        } catch (InstanceNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        U user = (U) authentication.getPrincipal();
        if (routeDay.getRoute().getUser().getUsername().equals(user.getUsername())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasStayPermission(Authentication authentication, Long id) {

        S stay = null;
        try {
            stay = this.routeService.getStayById(id);
        } catch (InstanceNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        U user = (U) authentication.getPrincipal();
        if (stay.getDay().getRoute().getUser().getUsername().equals(user.getUsername())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasStayPermission2(Authentication authentication, S stay) {

        System.out.println(stay.getId());
        return false;
    }
}
