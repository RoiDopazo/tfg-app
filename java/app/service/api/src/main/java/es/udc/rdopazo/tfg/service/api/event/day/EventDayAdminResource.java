package es.udc.rdopazo.tfg.service.api.event.day;

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
import es.udc.rdopazo.tfg.service.api.event.day.dto.EventDayPersistDto;

@Path("/admin/eventday/")
@Consumes(MediaType.APPLICATION_JSON)
public interface EventDayAdminResource extends Serializable {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<EventDayPersistDto> getAll(@DefaultValue("null") @QueryParam("event") String event,
            @DefaultValue("null") @QueryParam("day") String day,
            @DefaultValue("") @QueryParam("filterBy") String filter,
            @DefaultValue("") @QueryParam("value") String value,
            @DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count)
            throws InputValidationException, InstanceNotFoundException;

    @GET
    @Path("{idEvent}/day/{idDay}")
    @Produces(MediaType.APPLICATION_JSON)
    EventDayPersistDto getById(@PathParam("idEvent") String idEvent, @PathParam("idDay") String idDay,
            @DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count)
            throws InputValidationException, InstanceNotFoundException;

    @PUT
    @Path("{idEvent}/day/{idDay}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EventDayPersistDto update(@PathParam("idEvent") String idEvent, @PathParam("idDay") String idDay,
            EventDayPersistDto eventDayPersistDto) throws InputValidationException, InstanceNotFoundException;

    @POST
    @Path("{idEvent}")
    @Produces(MediaType.APPLICATION_JSON)
    EventDayPersistDto create(@PathParam("idEvent") String idEvent, EventDayPersistDto eventDayPersistDto)
            throws InstanceNotFoundException, InputValidationException;

    @DELETE
    @Path("{idEvent}/day/{idDay}")
    void delete(@PathParam("idEvent") String idEvent, @PathParam("idDay") String idDay)
            throws InputValidationException, InstanceNotFoundException;

}
