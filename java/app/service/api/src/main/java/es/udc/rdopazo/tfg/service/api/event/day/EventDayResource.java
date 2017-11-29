package es.udc.rdopazo.tfg.service.api.event.day;

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

import es.udc.rdopazo.tfg.service.api.event.day.dto.EventDayDto;

@Path("event/{idEvent}/day/")
public interface EventDayResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<EventDayDto> getAll(@PathParam("idEvent") String idEvent,
            @DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

    @GET
    @Path("{idDay}")
    @Produces(MediaType.APPLICATION_JSON)
    EventDayDto getById(@PathParam("idEvent") String idEvent, @PathParam("idDay") String idDay,
            @DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

    @PUT
    @Path("{idDay}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EventDayDto update(@PathParam("idEvent") String idEvent, @PathParam("idDay") String idDay,
            EventDayDto eventDayDto);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    EventDayDto create(@PathParam("idEvent") String idEvent, EventDayDto eventDayDto);

    @DELETE
    @Path("{idDay}")
    void delete(@PathParam("idEvent") String idEvent, @PathParam("idDay") String idDay);

}
