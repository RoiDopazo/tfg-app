package es.udc.rdopazo.tfg.service.api.event.place.dto;

import es.udc.rdopazo.tfg.service.api.event.day.dto.R_EventDayDto;
import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class R_EventPlaceDto implements EntityDto {

    private Long id;

    private String name;

    private String description;

    private Double lat;

    private Double lng;

    private String address;

    private Long startHour;

    private Long endHour;

    private R_EventDayDto day;

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
     * Returns the startHour
     *
     * @return The startHour
     */
    public Long getStartHour() {
        return this.startHour;
    }

    /**
     * Sets the startHour to given value
     *
     * @param startHour
     *            The startHour to set
     */
    public void setStartHour(Long startHour) {
        this.startHour = startHour;
    }

    /**
     * Returns the endHour
     *
     * @return The endHour
     */
    public Long getEndHour() {
        return this.endHour;
    }

    /**
     * Sets the endHour to given value
     *
     * @param endHour
     *            The endHour to set
     */
    public void setEndHour(Long endHour) {
        this.endHour = endHour;
    }

    /**
     * Returns the day
     * 
     * @return The day
     */
    public R_EventDayDto getDay() {
        return this.day;
    }

    /**
     * Sets the day to given value
     * 
     * @param day
     *            The day to set
     */
    public void setDay(R_EventDayDto day) {
        this.day = day;
    }

}
