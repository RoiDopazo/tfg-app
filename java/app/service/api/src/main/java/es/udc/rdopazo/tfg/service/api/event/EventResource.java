package es.udc.rdopazo.tfg.service.api.event;

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

import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.Role;
import es.udc.rdopazo.tfg.service.api.event.dto.EventDto;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Path("event")
public interface EventResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.MODERATOR, Role.USER })
    public List<EventDto> getAll(@DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.MODERATOR, Role.USER })
    public EventDto getById(@PathParam("id") String id) throws InstanceNotFoundException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.MODERATOR, Role.USER })
    public EventDto create(EventDto eventDto);

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.MODERATOR, Role.USER })
    public EventDto update(@PathParam("id") String id, EventDto eventDto);

    @DELETE
    @Path("{id}")
    @Secured({ Role.MODERATOR, Role.USER })
    public void delete(@PathParam("id") String id) throws InstanceNotFoundException;
}
