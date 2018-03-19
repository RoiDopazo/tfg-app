package es.udc.rdopazo.tfg.service.api.event.day;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
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
            @DefaultValue("null") @QueryParam("count") String count) throws InstanceNotFoundException;

}
