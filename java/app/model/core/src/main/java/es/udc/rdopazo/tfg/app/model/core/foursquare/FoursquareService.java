package es.udc.rdopazo.tfg.app.model.core.foursquare;

import fi.foyt.foursquare.api.entities.CompactVenue;

public interface FoursquareService {

    CompactVenue[] getPlacesByCity(String nombre, Integer limit, String idCategoria);

    String getPhoto(String lugar);

    Long getNumLikes(String lugar);
}
