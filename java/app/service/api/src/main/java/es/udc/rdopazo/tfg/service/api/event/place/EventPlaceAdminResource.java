package es.udc.rdopazo.tfg.service.api.event.place;

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
import es.udc.rdopazo.tfg.service.api.event.place.dto.EventPlacePersistDto;

@Path("/admin/eventplace")
public interface EventPlaceAdminResource extends Serializable {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EventPlacePersistDto> getAll(@DefaultValue("null") @QueryParam("event") String event,
            @DefaultValue("null") @QueryParam("day") String day,
            @DefaultValue("") @QueryParam("filterBy") String filter,
            @DefaultValue("") @QueryParam("value") String value,
            @DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count) throws InputValidationException;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EventPlacePersistDto getById(@PathParam("id") String id)
            throws InputValidationException, InstanceNotFoundException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EventPlacePersistDto create(@QueryParam("event") String idEvent, @QueryParam("day") String idDay,
            EventPlacePersistDto eventPlacePersistDto) throws InstanceNotFoundException, InputValidationException;

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EventPlacePersistDto update(@PathParam("id") String id, EventPlacePersistDto eventPlacePersistDto)
            throws InstanceNotFoundException, InputValidationException;

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") String id) throws InputValidationException, InstanceNotFoundException;
}
