package es.udc.rdopazo.tfg.app.service.core.util.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import es.udc.rdopazo.tfg.app.util.exceptions.UnUpdateableRouteException;
import es.udc.rdopazo.tfg.app.util.exceptions.dto.UnUpdateableRouteExceptionDto;

@Provider
public class UnUpdateableRouteExceptionMapper implements ExceptionMapper<UnUpdateableRouteException> {

    public Response toResponse(UnUpdateableRouteException ex) {
        return Response.status(Response.Status.NOT_FOUND).entity(
                new UnUpdateableRouteExceptionDto(ex.getInstanceId(), ex.getInstanceType(), ex.getInstanceValue()))
                .build();

    }
}
