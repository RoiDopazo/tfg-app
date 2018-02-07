package es.udc.rdopazo.tfg.service.api.usuario;

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
import javax.ws.rs.core.Response;

import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioDto;
import es.udc.rdopazo.tfg.service.api.util.Role;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Path("user")
public interface UsuarioResource {

    // CRUD

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured(Role.ADMIN)
    public List<UsuarioDto> getAll();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioDto getById(@PathParam("id") String id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioDto create(UsuarioDto usuarioDto);

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioDto update(@PathParam("id") String id, UsuarioDto usuarioDto);

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id);

    // END CRUD

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json")
    @Path("/authenticate")
    public Response authenticate(UsuarioDto usuarioDto);
}
