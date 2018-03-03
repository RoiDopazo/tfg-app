package es.udc.rdopazo.tfg.service.api.route.day.dto;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class RealTimeDataDto implements EntityDto {

    private String lat;

    private String lng;

    /**
     * Returns the lat
     *
     * @return The lat
     */
    public String getLat() {
        return this.lat;
    }

    /**
     * Sets the lat to given value
     *
     * @param lat
     *            The lat to set
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * Returns the lng
     *
     * @return The lng
     */
    public String getLng() {
        return this.lng;
    }

    /**
     * Sets the lng to given value
     *
     * @param lng
     *            The lng to set
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

}
