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
import es.udc.rdopazo.tfg.app.util.exceptions.CustomErrorException;
import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.UnUpdateableRouteException;
import es.udc.rdopazo.tfg.service.api.route.dto.RoutePersistDto;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Path("/admin/route")
@Secured({ Role.ADMIN })
public interface RouteAdminResource extends Serializable {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RoutePersistDto> getAll(@DefaultValue("") @QueryParam("user") String user,
            @DefaultValue("") @QueryParam("filterBy") String filter,
            @DefaultValue("") @QueryParam("value") String value, @DefaultValue("") @QueryParam("index") String index,
            @DefaultValue("") @QueryParam("count") String count) throws InputValidationException;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RoutePersistDto getById(@PathParam("id") String id)
            throws InstanceNotFoundException, InputValidationException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RoutePersistDto create(RoutePersistDto routePersistDto)
            throws CustomErrorException, InstanceNotFoundException;

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RoutePersistDto update(@PathParam("id") String id, RoutePersistDto rutaDto) throws InstanceNotFoundException,
            InputValidationException, CustomErrorException, UnUpdateableRouteException;

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") String id) throws InstanceNotFoundException, InputValidationException;
}
