package es.udc.rdopazo.tfg.service.api.foursquare;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.app.util.enums.Role;
import es.udc.rdopazo.tfg.service.api.place.dto.PlaceDto;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Path("foursquare")
@Secured({ Role.USER })
public interface FoursquareResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("searchPlaces")
    List<PlaceDto> searchPlaces(@QueryParam("route") String route, @QueryParam("lat") String lat,
            @QueryParam("lng") String lng, @QueryParam("near") String near, @QueryParam("intent") String intent,
            @QueryParam("radius") String radius, @QueryParam("query") String query, @QueryParam("limit") String limit,
            @QueryParam("category") String category, @QueryParam("photo") String photo);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("recommendedPlaces")
    List<PlaceDto> recommendedPlaces(@QueryParam("route") String route, @QueryParam("lat") String lat,
            @QueryParam("lng") String lng, @QueryParam("near") String near, @QueryParam("radius") String radius,
            @QueryParam("section") String section, @QueryParam("query") String query, @QueryParam("limit") String limit,
            @QueryParam("sortByDistance") String sortByDistance, @QueryParam("price") String price,
            @QueryParam("photo") String photo);

}
