package es.udc.rdopazo.tfg.service.api.usuario;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.Role;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioDto;
import es.udc.rdopazo.tfg.service.api.util.Secured;
import es.udc.rdopazo.tfg.service.api.util.TokenDto;

@Path("user")
public interface UsuarioResource extends Serializable {

    // CRUD

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public List<UsuarioDto> getAll();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public UsuarioDto getById(@PathParam("id") String id) throws InstanceNotFoundException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public UsuarioDto getUserInfo() throws InstanceNotFoundException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioDto create(UsuarioDto usuarioDto);

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    public UsuarioDto update(@PathParam("id") String id, UsuarioDto usuarioDto) throws InstanceNotFoundException;

    @DELETE
    @Path("{id}")
    @Secured({ Role.USER })
    public void delete(@PathParam("id") String id) throws InstanceNotFoundException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/authenticate")
    public TokenDto authenticate(UsuarioDto usuarioDto) throws ForbiddenException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/refreshtoken")
    public TokenDto refreshToken(String refreshToken) throws ForbiddenException, InstanceNotFoundException;
}
