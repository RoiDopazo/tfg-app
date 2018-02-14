package es.udc.rdopazo.tfg.app.model.core.util.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import es.udc.rdopazo.tfg.app.model.core.route.RouteService;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;

@Component("mySecurityService")
public class MySecurityService<U extends Usuario, R extends Route<?, U>> {

    @Autowired
    private RouteService<R> routeService;

    public boolean hasRoutePermission(Authentication authentication, Long id) {

        R route = this.routeService.getById(id);
        U user = (U) authentication.getPrincipal();
        if (route.getUser().getUsername().equals(user.getUsername())) {
            return true;
        } else {
            return false;
        }
    }
}
