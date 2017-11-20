package es.udc.rdopazo.tfg.service.api.route;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.route.dto.RouteDto;

@Path("route")
public interface RouteResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RouteDto> getAll(@DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RouteDto getById(@PathParam("id") String id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RouteDto create(RouteDto rutaDto);

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RouteDto update(@PathParam("id") String id, RouteDto rutaDto);

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id);
}
