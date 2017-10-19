package es.udc.rdopazo.tfg.service.api.dialugar;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.dialugar.dto.DiaLugarDto;

@Path("route/{idRoute}/day/{idDay}/place")
public interface DiaLugarResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DiaLugarDto> getAll(@PathParam("idRoute") String idRoute, @PathParam("idDay") String idDay,
            @DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DiaLugarDto create(@PathParam("idRoute") String idRoute, @PathParam("idDay") String idDay,
            DiaLugarDto persistDayPlace);

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("idRoute") String idRoute, @PathParam("idDay") String idDay,
            @PathParam("id") String id);
}
