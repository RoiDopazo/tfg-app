package es.udc.rdopazo.tfg.app.service.core.util.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import es.udc.rdopazo.tfg.app.util.exceptions.CustomErrorException;
import es.udc.rdopazo.tfg.app.util.exceptions.dto.CustomErrorExceptionDto;

@Provider
public class CustomErrorExceptionMapper implements ExceptionMapper<CustomErrorException> {

    public Response toResponse(CustomErrorException exception) {
        return Response.status(Response.Status.NOT_FOUND).entity(new CustomErrorExceptionDto(exception.getMessage()))
                .build();
    }

}
