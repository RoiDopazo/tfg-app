package es.udc.rdopazo.tfg.app.service.core.util.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.security.access.AccessDeniedException;

import es.udc.rdopazo.tfg.app.util.exceptions.dto.AccessDeniedExceptionDto;

@Provider
public class AccessDeniedExceptionMapper implements ExceptionMapper<AccessDeniedException> {

    public Response toResponse(AccessDeniedException exception) {
        return Response.status(Response.Status.FORBIDDEN).entity(new AccessDeniedExceptionDto(exception.getMessage()))
                .build();
    }

}
