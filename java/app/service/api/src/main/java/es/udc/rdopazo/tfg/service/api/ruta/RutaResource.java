package es.udc.rdopazo.tfg.service.api.ruta;

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

import es.udc.rdopazo.tfg.service.api.ruta.dto.RutaDto;

@Path("route")
public interface RutaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RutaDto> getAll(@QueryParam("index") String index, @QueryParam("count") String count);

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public RutaDto getById(@PathParam("id") String id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RutaDto create(RutaDto rutaDto);

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public RutaDto update(@PathParam("id") String id, RutaDto rutaDto);

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id);
}
