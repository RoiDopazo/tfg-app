package es.udc.rdopazo.tfg.service.api.place;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.place.dto.PlaceDto;
import es.udc.rdopazo.tfg.service.api.util.Role;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Path("place")
@Secured
public interface PlaceResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public List<PlaceDto> getAll();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public PlaceDto getById(@PathParam("id") String id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public PlaceDto create(PlaceDto lugarDto);

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public PlaceDto update(@PathParam("id") String id, PlaceDto lugarDto);

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id);

}
