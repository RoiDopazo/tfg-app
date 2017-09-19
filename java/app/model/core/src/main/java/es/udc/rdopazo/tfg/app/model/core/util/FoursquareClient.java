package es.udc.rdopazo.tfg.app.model.core.util;

import fi.foyt.foursquare.api.FoursquareApi;

public class FoursquareClient {

    private FoursquareApi foursquareApiClient;

    public FoursquareClient(String clientId, String idClientSecret, String secretRedirectUrl) {

        this.foursquareApiClient = new FoursquareApi(clientId, idClientSecret, secretRedirectUrl);
    }

    /**
     * Returns the foursquareApiClient
     * 
     * @return The foursquareApiClient
     */
    public FoursquareApi getFoursquareApiClient() {
        return this.foursquareApiClient;
    }

    /**
     * Sets the foursquareApiClient to given value
     * 
     * @param foursquareApiClient
     *            The foursquareApiClient to set
     */
    public void setFoursquareApiClient(FoursquareApi foursquareApiClient) {
        this.foursquareApiClient = foursquareApiClient;
    }

}
