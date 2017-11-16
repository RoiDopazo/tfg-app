package es.udc.rdopazo.tfg.service.api.visita;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.visita.dto.VisitaDto;

@Path("route/{idRoute}/day/")
public interface VisitaResource {

    @GET
    @Path("{idDay}/stay/example")
    @Produces(MediaType.APPLICATION_JSON)
    public List<VisitaDto> getAll(@PathParam("idRoute") String idRoute, @PathParam("idDay") String idDay,
            @DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

}
