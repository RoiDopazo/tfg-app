package es.udc.rdopazo.tfg.etravel.api.example2;

import java.io.Serializable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("example2")
public interface Example2Service extends Serializable {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello();

}
