package es.udc.rdopazo.tfg.service.api.foursquare;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import es.udc.rdopazo.tfg.service.api.place.dto.PlaceDto;
import es.udc.rdopazo.tfg.service.api.util.Role;
import es.udc.rdopazo.tfg.service.api.util.Secured;

@Path("foursquare")
public interface FoursquareResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("searchPlaces")
    @Secured({ Role.USER })
    List<PlaceDto> searchPlaces(@QueryParam("route") String route, @QueryParam("lat") String lat,
            @QueryParam("lng") String lng, @QueryParam("near") String near, @QueryParam("intent") String intent,
            @QueryParam("radius") String radius, @QueryParam("query") String query, @QueryParam("limit") String limit,
            @QueryParam("category") String category, @QueryParam("photo") String photo);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("recommendedPlaces")
    @Secured({ Role.USER })
    List<PlaceDto> recommendedPlaces(@QueryParam("route") String route, @QueryParam("lat") String lat,
            @QueryParam("lng") String lng, @QueryParam("near") String near, @QueryParam("radius") String radius,
            @QueryParam("section") String section, @QueryParam("query") String query, @QueryParam("limit") String limit,
            @QueryParam("sortByDistance") String sortByDistance, @QueryParam("price") String price,
            @QueryParam("photo") String photo);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getPlacesByCoord")
    @Secured({ Role.USER })
    List<PlaceDto> getPlacesByCoord(@QueryParam("route") String route, @QueryParam("name") String nombre,
            @QueryParam("limit") String limit, @QueryParam("category") String category,
            @QueryParam("photo") String photos, List<String> categorias);

    @Path("getcoord")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.USER })
    String getCoord(@QueryParam("lat") String lat, @QueryParam("lng") String lng, @QueryParam("time") String time);

    @Path("getFoursquareCategories")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({ Role.ADMIN })
    String getFoursquareCategories();

}
