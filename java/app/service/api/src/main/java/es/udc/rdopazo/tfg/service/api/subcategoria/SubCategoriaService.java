package es.udc.rdopazo.tfg.service.api.subcategoria;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.subcategoria.dto.SubCategoriaDto;

@Path("subcategory")
public interface SubCategoriaService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SubCategoriaDto> getAll();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SubCategoriaDto getById(@PathParam("id") String id);
}
