package es.udc.rdopazo.tfg.app.service.core.util.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.dto.InstanceNotFoundExceptionDto;

@Provider
public class InstanceNotFoundExceptionMapper implements ExceptionMapper<InstanceNotFoundException> {

    public Response toResponse(InstanceNotFoundException ex) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new InstanceNotFoundExceptionDto(ex.getInstanceId(), ex.getInstanceType())).build();

    }
}
