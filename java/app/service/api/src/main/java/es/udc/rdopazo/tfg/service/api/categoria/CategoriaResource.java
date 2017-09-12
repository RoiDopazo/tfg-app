package es.udc.rdopazo.tfg.service.api.categoria;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.categoria.dto.CategoriaDto;

@Path("category")
public interface CategoriaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoriaDto> getAll();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoriaDto getById(@PathParam("id") String id);
}
