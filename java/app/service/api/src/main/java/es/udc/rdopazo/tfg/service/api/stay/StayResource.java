package es.udc.rdopazo.tfg.service.api.stay;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.stay.dto.StayDto;

@Path("route/{idRoute}/day/")
public interface StayResource {

    @GET
    @Path("{idDay}/stay/example")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StayDto> getAll(@PathParam("idRoute") String idRoute, @PathParam("idDay") String idDay,
            @DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

}
