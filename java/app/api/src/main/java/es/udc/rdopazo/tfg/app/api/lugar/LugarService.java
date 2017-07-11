package es.udc.rdopazo.tfg.app.api.lugar;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.app.api.lugar.dto.LugarDto;

@Path("lugar")
public interface LugarService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LugarDto> getAll();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LugarDto getById(@PathParam("id") String id);

}
