package es.udc.rdopazo.tfg.service.api.event.place.dto;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class EventPlaceDto implements EntityDto {

    private Long id;

    private String name;

    private String description;

    private Double lat;

    private Double lng;

    private String address;

    private Long startDate;

    private Long endDate;

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
     * Returns the name
     *
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name to given value
     *
     * @param name
     *            The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description
     *
     * @return The description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description to given value
     *
     * @param description
     *            The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the lat
     *
     * @return The lat
     */
    public Double getLat() {
        return this.lat;
    }

    /**
     * Sets the lat to given value
     *
     * @param lat
     *            The lat to set
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * Returns the lng
     *
     * @return The lng
     */
    public Double getLng() {
        return this.lng;
    }

    /**
     * Sets the lng to given value
     *
     * @param lng
     *            The lng to set
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

    /**
     * Returns the address
     *
     * @return The address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets the address to given value
     *
     * @param address
     *            The address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the startDate
     *
     * @return The startDate
     */
    public Long getStartDate() {
        return this.startDate;
    }

    /**
     * Sets the startDate to given value
     *
     * @param startDate
     *            The startDate to set
     */
    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the endDate
     *
     * @return The endDate
     */
    public Long getEndDate() {
        return this.endDate;
    }

    /**
     * Sets the endDate to given value
     *
     * @param endDate
     *            The endDate to set
     */
    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

}
