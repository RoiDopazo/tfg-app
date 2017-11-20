package es.udc.rdopazo.tfg.service.api.foursquare;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.place.dto.PlaceDto;

@Path("foursquare")
public interface FoursquareResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getPlacesByCity")
    List<PlaceDto> getPlacesByCity(@QueryParam("route") String route, @QueryParam("name") String nombre,
            @QueryParam("limit") String limit, @QueryParam("category") String category,
            @QueryParam("photo") String photos);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getPlacesByCoord")
    List<PlaceDto> getPlacesByCoord(@QueryParam("route") String route, @QueryParam("name") String nombre,
            @QueryParam("limit") String limit, @QueryParam("category") String category,
            @QueryParam("photo") String photos, List<String> categorias);

    @Path("getcoord")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getCoord(@QueryParam("lat") String lat, @QueryParam("lng") String lng, @QueryParam("time") String time);

}
