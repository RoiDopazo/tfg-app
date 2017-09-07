package es.udc.rdopazo.tfg.service.api.comentario;

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

import es.udc.rdopazo.tfg.service.api.comentario.dto.ComentarioDto;

@Path("comment")
public interface ComentarioService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ComentarioDto> getAll();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ComentarioDto getById(@PathParam("id") String id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ComentarioDto create(ComentarioDto comentarioDto);

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ComentarioDto update(@PathParam("id") String id, ComentarioDto comentarioDto);

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id);
}
