package es.udc.rdopazo.tfg.app.model.persistence.api.event.place;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;

public interface EventPlace<D extends EventDay<?, ?>> extends Entity<Long> {

    /**
     * Returns the id
     *
     * @return The id
     */
    public Long getId();

    /**
     * Sets the id to given value
     *
     * @param id
     *            The id to set
     */
    public void setId(Long id);

    /**
     * Returns the name
     *
     * @return The name
     */
    public String getName();

    /**
     * Sets the name to given value
     *
     * @param name
     *            The name to set
     */
    public void setName(String name);

    /**
     * Returns the description
     *
     * @return The description
     */
    public String getDescription();

    /**
     * Sets the description to given value
     *
     * @param description
     *            The description to set
     */
    public void setDescription(String description);

    /**
     * Returns the lat
     *
     * @return The lat
     */
    public Double getLat();

    /**
     * Sets the lat to given value
     *
     * @param lat
     *            The lat to set
     */
    public void setLat(Double lat);

    /**
     * Returns the lng
     *
     * @return The lng
     */
    public Double getLng();

    /**
     * Sets the lng to given value
     *
     * @param lng
     *            The lng to set
     */
    public void setLng(Double lng);

    /**
     * Returns the address
     *
     * @return The address
     */
    public String getAddress();

    /**
     * Sets the address to given value
     *
     * @param address
     *            The address to set
     */
    public void setAddress(String address);

    /**
     * Returns the startDate
     *
     * @return The startDate
     */
    public Long getStartDate();

    /**
     * Sets the startDate to given value
     *
     * @param startDate
     *            The startDate to set
     */
    public void setStartDate(Long startDate);

    /**
     * Returns the endDate
     *
     * @return The endDate
     */
    public Long getEndDate();

    /**
     * Sets the endDate to given value
     *
     * @param endDate
     *            The endDate to set
     */
    public void setEndDate(Long endDate);

    /**
     * Returns the day
     *
     * @return The day
     */
    public D getDay();

    /**
     * Sets the day to given value
     *
     * @param day
     *            The day to set
     */
    public void setDay(D day);

}
