package es.udc.rdopazo.tfg.app.model.core.foursquare;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.Recommendation;

public interface FoursquareService<C extends Category> {

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

    List<C> getFoursquareCategories();
}
