package es.udc.rdopazo.tfg.app.application.resteasy.util.filter;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Random;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

import es.udc.rdopazo.tfg.app.application.resteasy.spring.SpringApplicationContext;
import es.udc.rdopazo.tfg.app.model.core.usuario.UsuarioService;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class TokenAuthenticatorFilter implements ContainerResponseFilter {

    private static final String AUTHENTICATION_SCHEME = "Bearer";

    private static final String AUTHENTICATION_SCHEME_2 = "X";

    @SuppressWarnings("unchecked")
    UsuarioService<Usuario> usuarioService = (UsuarioService<Usuario>) SpringApplicationContext
            .getBean(UsuarioService.class);

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (!this.isTokenBasedAuthentication(authorizationHeader)) {
            this.abortWithUnauthorized(requestContext);
            return;
        }

        if (authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase())) {
            String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
            if (this.validateToken(token)) {
                this.generateToken(authorizationHeader, responseContext, token);
            } else {
                throw new NotAuthorizedException("No valid Token");
            }

        } else if (authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME_2.toLowerCase())) {

            this.generateToken(authorizationHeader, responseContext, null);
        }

    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        if (authorizationHeader != null) {
            return authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ")
                    || (authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME_2.toLowerCase() + " "));
        } else {
            return false;
        }
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME).build());
    }

    private boolean validateToken(String token) {

        String strToken = Base64.decodeAsString(token);
        String[] splitToken = strToken.split("-");
        return this.usuarioService.evaluateToken(splitToken[1], token);
    }

    private void generateToken(String authorizationHeader, ContainerResponseContext responseContext, String lastToken) {

        String username = authorizationHeader.substring(AUTHENTICATION_SCHEME_2.length()).trim();
        String usuario = null;
        Boolean result = null;
        if (authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME_2.toLowerCase())) {
            result = (Boolean) responseContext.getEntity();
            usuario = this.usuarioService.getByField("nombre", username.toLowerCase()).get(0).getNombre();
        } else {
            result = true;
            String strToken = Base64.decodeAsString(lastToken);
            String[] splitToken = strToken.split("-");
            usuario = splitToken[1];
        }
        if (result) {

            Random rand = new Random();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String strToken = rand.nextInt(99999 + 1) + "-" + usuario + "-" + timestamp.getTime();
            String token = Base64.encodeAsString(strToken);
            this.usuarioService.setToken(usuario, token);
            responseContext.getHeaders().add("X-Authentication", token);
        }
    }

}
