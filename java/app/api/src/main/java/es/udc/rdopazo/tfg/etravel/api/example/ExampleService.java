package es.udc.rdopazo.tfg.etravel.api.example;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("example")
public interface ExampleService extends Serializable {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ExampleDto> sayHelloDto();
}
