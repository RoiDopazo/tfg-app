package es.udc.rdopazo.tfg.service.api.route.day;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.Role;
import es.udc.rdopazo.tfg.service.api.route.day.dto.RouteDayPersistDto;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Path("/admin/routeDay")
@Consumes(MediaType.APPLICATION_JSON)
@Secured({ Role.ADMIN })
public interface RouteDayAdminResource extends Serializable {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<RouteDayPersistDto> getAll(@DefaultValue("") @QueryParam("route") String route,
            @DefaultValue("") @QueryParam("filterBy") String filter,
            @DefaultValue("") @QueryParam("value") String value, @DefaultValue("") @QueryParam("index") String index,
            @DefaultValue("") @QueryParam("count") String count) throws InputValidationException;

    @GET
    @Path("{idRoute}/day/{idDay}")
    @Produces(MediaType.APPLICATION_JSON)
    RouteDayPersistDto getById(@PathParam("idRoute") String idRoute, @PathParam("idDay") String idDay)
            throws InputValidationException, InstanceNotFoundException;

    @PUT
    @Path("{idRoute}/day/{idDay}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RouteDayPersistDto update(@PathParam("idRoute") String idRoute, @PathParam("idDay") String idDay,
            RouteDayPersistDto diaDto) throws InputValidationException, InstanceNotFoundException;

    @POST
    @Path("{idRoute}")
    @Produces(MediaType.APPLICATION_JSON)
    RouteDayPersistDto create(@PathParam("idRoute") String idRoute, RouteDayPersistDto routeDayPersistDto)
            throws InstanceNotFoundException, InputValidationException;

    @POST
    @Path("/{idRoute}/setNumDays")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<RouteDayPersistDto> createNumDays(@PathParam("idRoute") String idRoute, Integer numDays)
            throws InstanceNotFoundException, InputValidationException;

}
