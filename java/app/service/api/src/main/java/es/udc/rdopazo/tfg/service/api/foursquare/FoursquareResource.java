package es.udc.rdopazo.tfg.service.api.foursquare;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.lugar.dto.LugarDto;

@Path("foursquare")
public interface FoursquareResource {

    @Path("findPlaces")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<LugarDto> getPlacesByCity(@QueryParam("nombre") String nombre, @QueryParam("categoria") String idCategoria,
            @QueryParam("photos") String photos);

}
