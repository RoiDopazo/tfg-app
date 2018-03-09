package es.udc.rdopazo.tfg.app.client.resteasy.filter;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;

import es.udc.rdopazo.tfg.app.client.resteasy.exception.InputValidationWebApplicationException;

@Provider
public class ClientErrorFilter implements ClientResponseFilter {

    private static ObjectMapper _MAPPER = new ObjectMapper();

    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {

        if (responseContext.getStatus() != Response.Status.OK.getStatusCode()) {
            if (responseContext.hasEntity()) {

                Exception error = _MAPPER.readValue(responseContext.getEntityStream(), Exception.class);
                String message = error.getMessage();

                Response.Status status = Response.Status.fromStatusCode(responseContext.getStatus());

                switch (status) {
                case BAD_REQUEST:
                    System.out.println("BADREQ");
                    throw new InputValidationWebApplicationException("asdasd");

                }
            }
        }
    }
}
