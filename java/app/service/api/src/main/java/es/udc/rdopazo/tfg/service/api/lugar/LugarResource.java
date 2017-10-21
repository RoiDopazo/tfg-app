package es.udc.rdopazo.tfg.service.api.lugar;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.lugar.dto.LugarDto;

@Path("place")
public interface LugarResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LugarDto> getAll();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LugarDto getById(@PathParam("id") String id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LugarDto create(LugarDto lugarDto);

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LugarDto update(@PathParam("id") String id, LugarDto lugarDto);

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id);

    // Obtiene la lista de días que un lugar está en una ruta
    @GET
    @Path("/getListDaysBy")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean[] getListDaysBy(@QueryParam("route") String route, @QueryParam("idFoursquare") String idFoursquare);
}
