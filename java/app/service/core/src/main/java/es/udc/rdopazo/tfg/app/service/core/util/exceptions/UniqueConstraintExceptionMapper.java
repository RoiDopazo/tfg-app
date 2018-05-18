package es.udc.rdopazo.tfg.app.service.core.util.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import es.udc.rdopazo.tfg.app.util.exceptions.UniqueConstraintException;
import es.udc.rdopazo.tfg.app.util.exceptions.dto.UniqueConstraintExceptionDto;

@Provider
public class UniqueConstraintExceptionMapper implements ExceptionMapper<UniqueConstraintException> {

    public Response toResponse(UniqueConstraintException ex) {
        return Response.status(Response.Status.NOT_FOUND).entity(
                new UniqueConstraintExceptionDto(ex.getInstanceId(), ex.getInstanceType(), ex.getInstanceValue()))
                .build();

    }
}
