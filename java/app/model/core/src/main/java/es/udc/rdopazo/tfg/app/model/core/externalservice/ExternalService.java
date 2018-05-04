package es.udc.rdopazo.tfg.app.model.core.externalservice;

import java.util.List;

import com.google.maps.model.DistanceMatrix;

import es.udc.rdopazo.tfg.app.model.persistence.api.category.Category;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.Recommendation;

public interface ExternalService<C extends Category> {

    CompactVenue[] getFSPlacesByCity(String nombre, Integer limit, String idCategoria);

    String getFSPhoto(String lugar);

    Long getFSNumLikes(String lugar);

    Recommendation[] recommendedFSPlaces(String lat, String lng, Integer radius, String section, String query,
            Integer limit, Integer sortByDistance, String price);

    Recommendation[] recommendedFSPlaces(String near, Integer radius, String section, String query, Integer limit,
            Integer sortByDistance, String price);

    CompactVenue[] searchFSPlaces(String lat, String lng, String intent, Integer radius, String query, Integer limit,
            String categoryId);

    CompactVenue[] searchFSPlaces(String near, String intent, Integer radius, String query, Integer limit,
            String categoryId);

    List<C> getFSCategories();

    DistanceMatrix getGMTravelInfo(double oriLat, double oriLng, double destLat, double destLng, String travelMode);

}
