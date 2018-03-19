package es.udc.rdopazo.tfg.service.api.stay;

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

import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.Role;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayPersistDto;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Path("/admin/stay")
@Secured({ Role.ADMIN })
public interface StayAdminResource extends Serializable {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<StayPersistDto> getAll(@DefaultValue("null") @QueryParam("route") String route,
            @DefaultValue("null") @QueryParam("day") String day,
            @DefaultValue("null") @QueryParam("filterBy") String filter,
            @DefaultValue("null") @QueryParam("value") String value,
            @DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count) throws InputValidationException;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public StayPersistDto getById(@PathParam("id") String id)
            throws InstanceNotFoundException, InputValidationException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StayPersistDto create(StayPersistDto stayPersistDto)
            throws InstanceNotFoundException, InputValidationException;

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StayPersistDto update(@PathParam("id") String id, StayPersistDto stayPersistDto)
            throws InstanceNotFoundException, InputValidationException;

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") String id) throws InputValidationException, InstanceNotFoundException;

}
