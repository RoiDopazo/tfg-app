package es.udc.rdopazo.tfg.etravel.application.resteasy.util.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.apache.http.HttpStatus;

import es.udc.rdopazo.tfg.service.api.util.annotations.ToLoggin;

@ToLoggin
@Provider
@Priority(Priorities.AUTHENTICATION)
public class TokenInitializeFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        if (responseContext.getStatus() == HttpStatus.SC_OK) {
            responseContext.getHeaders().add("X-Authentication", "1234");
        }
    }

}
