package es.udc.rdopazo.tfg.service.api.google;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.stay.dto.StayDto;

@Path("google")
public interface GoogleResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("distanceMatrix/batch")
    List<StayDto> getTravelInfoBatch(List<StayDto> stays);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("distanceMatrix")
    List<Long> getTravelInfo(@QueryParam("oriLat") String oriLat, @QueryParam("oriLng") String oriLng,
            @QueryParam("dstLat") String dstLat, @QueryParam("dstLng") String dstLng,
            @QueryParam("mode") String travelMode);
}
