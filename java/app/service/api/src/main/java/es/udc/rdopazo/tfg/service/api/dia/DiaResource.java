package es.udc.rdopazo.tfg.service.api.dia;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.dia.dto.DiaDto;
import es.udc.rdopazo.tfg.service.api.dia.dto.DiaPersistDto;

@Path("route/{idRoute}/day")
public interface DiaResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<DiaDto> getAll(@PathParam("idRoute") String idRoute, @DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

    @PUT
    @Path("{idDay}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DiaDto update(@PathParam("idRoute") String idRoute, @PathParam("idDay") String idDay, DiaDto diaDto);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    DiaDto create(@PathParam("idRoute") String idRoute, DiaPersistDto persistDay);

    @POST
    @Path("/setNumDays")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<DiaDto> createNumDays(@PathParam("idRoute") String idRoute, Integer numDays);

    @DELETE
    void delete(@PathParam("idRoute") String idRoute);

    @POST
    @Path("/calculateHours")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    DiaDto calculateHours(@PathParam("idRoute") String idRoute, DiaDto diaDto);
}
