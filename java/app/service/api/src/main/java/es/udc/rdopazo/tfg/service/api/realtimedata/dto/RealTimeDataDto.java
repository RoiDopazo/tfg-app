package es.udc.rdopazo.tfg.service.api.realtimedata.dto;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class RealTimeDataDto implements EntityDto {

    private static final long serialVersionUID = -8354585801517075872L;

    private Long id;

    private String locations;

    /**
     * Returns the id
     *
     * @return The id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets the id to given value
     *
     * @param id
     *            The id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the locations
     *
     * @return The locations
     */
    public String getLocations() {
        return this.locations;
    }

    /**
     * Sets the locations to given value
     *
     * @param locations
     *            The locations to set
     */
    public void setLocations(String locations) {
        this.locations = locations;
    }

}
