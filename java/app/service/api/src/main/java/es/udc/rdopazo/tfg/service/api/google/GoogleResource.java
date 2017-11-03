package es.udc.rdopazo.tfg.service.api.google;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("google")
public interface GoogleResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    void getTravelInfo();
}
