package es.udc.rdopazo.tfg.service.api.stay;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.stay.dto.StayDto;
import es.udc.rdopazo.tfg.service.api.stay.dto.StayPlaceDto;

@Path("stay")
public interface StayResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<StayDto> getAll(@QueryParam("idRoute") String idRoute, @QueryParam("idDay") String idDay,
            @DefaultValue("null") @QueryParam("index") String index,
            @DefaultValue("null") @QueryParam("count") String count);

    @GET
    @Path("{idStay}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StayDto> getById(@PathParam("idStay") String idStay);

    @POST
    @Path("place")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public StayPlaceDto createP(@QueryParam("idRoute") String idRoute, @QueryParam("idDay") String idDay,
            StayPlaceDto stayPlaceDto);

}
