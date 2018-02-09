package es.udc.rdopazo.tfg.app.application.resteasy.util.filter;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import es.udc.rdopazo.tfg.app.service.core.util.TokenServices;
import es.udc.rdopazo.tfg.service.api.util.Role;
import es.udc.rdopazo.tfg.service.api.util.Secured;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@Provider
@Priority(Priorities.AUTHENTICATION)
@Secured
public class TokenAuthenticatorFilter2 implements ContainerRequestFilter {

    private static final String AUTHENTICATION_SCHEME = "Bearer ";

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if ((authorizationHeader == null) || !authorizationHeader.startsWith(AUTHENTICATION_SCHEME)) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            Role userRole = Role.valueOf(TokenServices.validateToken(token));
            List<Role> classRoles = this.extractRoles(this.resourceInfo.getResourceClass());
            List<Role> methodRoles = this.extractRoles(this.resourceInfo.getResourceMethod());
            if (!userRole.equals(Role.ADMIN)) {
                if (!methodRoles.contains(userRole)) {
                    requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
                }

                // if (!classRoles.contains(userRole)) {
                // requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
                // }

            }
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
                | IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(TokenServices.getUserId(token), "role", null));
        Authentication s = SecurityContextHolder.getContext().getAuthentication();
        Object prin = s.getPrincipal();
        System.out.println(prin.toString());
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
