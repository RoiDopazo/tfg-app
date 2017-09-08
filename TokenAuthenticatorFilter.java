package es.udc.rdopazo.tfg.etravel.application.resteasy.util.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.dao.UsuarioDao;
import es.udc.rdopazo.tfg.service.api.util.annotations.Secured;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class TokenAuthenticatorFilter implements ContainerRequestFilter {

    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Autowired
    UsuarioDao userService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (!this.isTokenBasedAuthentication(authorizationHeader)) {
            this.abortWithUnauthorized(requestContext);
            return;
        }

        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            this.validateToken(token);
        } catch (Exception e) {
            this.abortWithUnauthorized(requestContext);
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        return (authorizationHeader != null)
                && authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME).build());
    }

    private String validateToken(String token) throws Exception {

        if (!TokenEncription.areKeysPresent()) {
            TokenEncription.generateKey();
        }
        Object x = TokenEncription.encrypt(token);

        return "sad";
    }

}