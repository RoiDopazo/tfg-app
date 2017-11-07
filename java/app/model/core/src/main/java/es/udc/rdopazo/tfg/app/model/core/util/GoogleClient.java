package es.udc.rdopazo.tfg.app.model.core.util;

import com.google.maps.GeoApiContext;

public class GoogleClient {

    private GeoApiContext googleApiContext;

    public GoogleClient(String apikey) {

        this.googleApiContext = new GeoApiContext.Builder().apiKey(apikey).build();
    }

    /**
     * Returns the googleApiContext
     * 
     * @return The googleApiContext
     */
    public GeoApiContext getGoogleApiContext() {
        return this.googleApiContext;
    }

    /**
     * Sets the googleApiContext to given value
     * 
     * @param googleApiContext
     *            The googleApiContext to set
     */
    public void setGoogleApiContext(GeoApiContext googleApiContext) {
        this.googleApiContext = googleApiContext;
    }

}
