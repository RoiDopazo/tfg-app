package es.udc.rdopazo.tfg.service.api.realtimedata.dto;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class RealTimeDataPersistDto implements EntityDto {

    private static final long serialVersionUID = 2352086641287489926L;

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
