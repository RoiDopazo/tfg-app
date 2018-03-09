package es.udc.rdopazo.tfg.app.service.core.util.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.dto.InputValidationExceptionDto;

@Provider
public class InputValidationExceptionMapper implements ExceptionMapper<InputValidationException> {

    public Response toResponse(InputValidationException ex) {
        return Response.status(Response.Status.BAD_REQUEST).entity(new InputValidationExceptionDto(ex.getMessage()))
                .build();

    }
}
