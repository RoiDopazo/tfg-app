package es.udc.rdopazo.tfg.service.api.event.place;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.event.place.dto.EventPlaceDto;

@Path("eventplace")
public interface EventPlaceResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EventPlaceDto> getAll(@DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EventPlaceDto getById(@PathParam("id") String id) throws InstanceNotFoundException;
}
