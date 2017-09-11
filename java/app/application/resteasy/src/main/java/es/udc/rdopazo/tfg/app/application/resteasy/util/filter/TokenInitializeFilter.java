package es.udc.rdopazo.tfg.app.application.resteasy.util.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.apache.http.HttpStatus;

import es.udc.rdopazo.tfg.service.api.util.ToLoggin;

@ToLoggin
@Provider
@Priority(Priorities.AUTHENTICATION)
public class TokenInitializeFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        String strToken = null;
        if (responseContext.getStatus() == HttpStatus.SC_OK) {
            strToken = "roi||1234";
            Object token = null;
            try {
                token = TokenEncription.encrypt(strToken);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            responseContext.getHeaders().add("X-Authentication", token.toString());
        }
    }

}
