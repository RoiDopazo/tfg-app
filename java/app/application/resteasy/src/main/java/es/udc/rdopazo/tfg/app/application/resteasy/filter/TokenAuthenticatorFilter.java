package es.udc.rdopazo.tfg.app.application.resteasy.filter;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import es.udc.rdopazo.tfg.app.application.resteasy.spring.SpringApplicationContext;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.service.core.util.TokenServices;
import es.udc.rdopazo.tfg.app.util.enums.Role;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.dto.ExpiredTokenExceptionDto;
import es.udc.rdopazo.tfg.service.api.util.Secured;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@Provider
@Priority(Priorities.AUTHENTICATION)
@Secured
public class TokenAuthenticatorFilter<U extends Usuario> implements ContainerRequestFilter {

    private static final String AUTHENTICATION_SCHEME = "Bearer ";

    @Context
    private ResourceInfo resourceInfo;

    @SuppressWarnings("unchecked")
    private TokenServices<U> tokenService = (TokenServices<U>) SpringApplicationContext.getBean(TokenServices.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        boolean abort = false;

        if ((authorizationHeader == null) || !authorizationHeader.startsWith(AUTHENTICATION_SCHEME)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        } else {
            String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
            Role userRole = null;
            try {
                userRole = Role.valueOf(this.tokenService.validateToken(token));
            } catch (UnsupportedJwtException | MalformedJwtException | SignatureException
                    | IllegalArgumentException e) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            } catch (ExpiredJwtException ex) {
                abort = true;
                ExpiredTokenExceptionDto exp = new ExpiredTokenExceptionDto("ExpiredJwtToken",
                        "Error validating access token.");
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(exp).build());
            }

            if (!abort) {

                List<Role> classRoles = this.extractRoles(this.resourceInfo.getResourceClass());
                List<Role> methodRoles = this.extractRoles(this.resourceInfo.getResourceMethod());
                if (!methodRoles.contains(userRole)) {
                    if (!classRoles.contains(userRole)) {
                        requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
                    }
                }

                List<SimpleGrantedAuthority> listRoles = new ArrayList<SimpleGrantedAuthority>();
                String springRole = "ROLE_" + userRole.name().toUpperCase();
                listRoles.add(new SimpleGrantedAuthority(springRole));

                try {
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(this.tokenService.getUser(token), "", listRoles));
                } catch (InstanceNotFoundException e) {
                    requestContext.abortWith(Response.status(Response.Status.NOT_FOUND).build());
                }
            }
        }

    }

    private List<Role> extractRoles(AnnotatedElement annotatedElement) {
        List<Role> roles = new ArrayList<>();
        if (annotatedElement == null) {
            return roles;
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return roles;
            } else {
                Role[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }

}
