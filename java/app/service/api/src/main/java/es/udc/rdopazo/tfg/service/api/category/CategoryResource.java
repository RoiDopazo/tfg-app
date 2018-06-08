package es.udc.rdopazo.tfg.service.api.category;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.app.util.enums.Role;
import es.udc.rdopazo.tfg.app.util.exceptions.InstanceNotFoundException;
import es.udc.rdopazo.tfg.service.api.category.dto.CategoryDto;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Path("category")
@Secured({ Role.USER })
public interface CategoryResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryDto> getAll();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public CategoryDto getById(@PathParam("id") String id) throws InstanceNotFoundException;
}
