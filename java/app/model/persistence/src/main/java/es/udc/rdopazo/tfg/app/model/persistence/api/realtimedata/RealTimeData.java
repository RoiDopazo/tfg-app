package es.udc.rdopazo.tfg.app.model.persistence.api.realtimedata;

import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;

public interface RealTimeData extends Entity<Long> {

    /**
     * Returns the id
     *
     * @return The id
     */
    Long getId();

    /**
     * Sets the id to given value
     *
     * @param id
     *            The id to set
     */
    void setId(Long id);

    /**
     * Returns the locations
     *
     * @return The locations
     */
    String getLocations();

    /**
     * Sets the locations to given value
     *
     * @param locations
     *            The locations to set
     */
    void setLocations(String locations);

    void appendLocations(String locations);
}
