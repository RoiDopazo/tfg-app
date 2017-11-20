package es.udc.rdopazo.tfg.service.api.subcategory;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.subcategory.dto.SubCategoryDto;

@Path("subcategory")
public interface SubCategoryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SubCategoryDto> getAll();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SubCategoryDto getById(@PathParam("id") String id);

}
