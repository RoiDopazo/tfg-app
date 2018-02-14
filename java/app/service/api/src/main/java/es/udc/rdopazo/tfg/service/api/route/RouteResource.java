package es.udc.rdopazo.tfg.service.api.route;

import java.io.Serializable;
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
import es.udc.rdopazo.tfg.service.api.util.Role;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Path("route")
@Secured
public interface RouteResource extends Serializable {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public List<RouteDto> getAll(@DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public RouteDto getById(@PathParam("id") String id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public RouteDto create(RouteDto rutaDto);

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public RouteDto update(@PathParam("id") String id, RouteDto rutaDto);

    @DELETE
    @Path("{id}")
    @Secured({ Role.USER })
    public void delete(@PathParam("id") String id);
}
