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

import es.udc.rdopazo.tfg.app.util.enums.Role;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.UnUpdateableRouteException;
import es.udc.rdopazo.tfg.service.api.route.dto.RouteDto;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Path("route")
@Secured({ Role.USER })
public interface RouteResource extends Serializable {

    @GET
    @Path("/explore")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public List<RouteDto> explore(@DefaultValue("") @QueryParam("city") String city,
            @DefaultValue("") @QueryParam("state") String state,
            @DefaultValue("") @QueryParam("numDays") String numDays,
            @DefaultValue("") @QueryParam("maxDistance") String maxDistance,
            @DefaultValue("") @QueryParam("maxDuration") String maxDuration,
            @DefaultValue("") @QueryParam("index") String index, @DefaultValue("") @QueryParam("count") String count)
            throws InputValidationException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/user/owner")
    @Secured({ Role.USER })
    public List<RouteDto> getOwnRoutes(@DefaultValue("") @QueryParam("filterBy") String filter,
            @DefaultValue("") @QueryParam("value") String value, @DefaultValue("") @QueryParam("index") String index,
            @DefaultValue("") @QueryParam("count") String count) throws InputValidationException;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RouteDto getById(@PathParam("id") String id) throws InstanceNotFoundException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RouteDto create(RouteDto rutaDto);

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RouteDto update(@PathParam("id") String id, RouteDto rutaDto)
            throws InstanceNotFoundException, UnUpdateableRouteException;

    @PUT
    @Path("{id}/updatepriv")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public RouteDto updatePriv(@PathParam("id") String id, RouteDto routeDto)
            throws InstanceNotFoundException, InputValidationException;

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id) throws InstanceNotFoundException;
}
