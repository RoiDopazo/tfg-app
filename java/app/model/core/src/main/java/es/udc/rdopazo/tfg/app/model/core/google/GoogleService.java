package es.udc.rdopazo.tfg.app.model.core.google;

import com.google.maps.model.DistanceMatrix;

public interface GoogleService {

    DistanceMatrix getTravelInfo(double oriLat, double oriLng, double destLat, double destLng, String travelMode);

}
