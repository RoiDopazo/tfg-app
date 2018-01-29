package es.udc.rdopazo.tfg.app.model.core.foursquare;

import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.Recommendation;

public interface FoursquareService {

    CompactVenue[] getPlacesByCity(String nombre, Integer limit, String idCategoria);

    String getPhoto(String lugar);

    Long getNumLikes(String lugar);

    Recommendation[] recommendedPlaces(String lat, String lng, Integer radius, String section, String query,
            Integer limit, Integer sortByDistance, String price);

    Recommendation[] recommendedPlaces(String near, Integer radius, String section, String query, Integer limit,
            Integer sortByDistance, String price);

    CompactVenue[] searchPlaces(String lat, String lng, String intent, Integer radius, String query, Integer limit,
            String categoryId);

    CompactVenue[] searchPlaces(String near, String intent, Integer radius, String query, Integer limit,
            String categoryId);
}
