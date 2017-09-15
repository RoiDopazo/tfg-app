package es.udc.rdopazo.tfg.app.model.core.foursquare;

import fi.foyt.foursquare.api.entities.CompactVenue;

public interface FoursquareService {

    CompactVenue[] obtenerLugaresCiudad(String nombre);
}
