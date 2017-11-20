package es.udc.rdopazo.tfg.service.api.dialugar.dto;

import es.udc.rdopazo.tfg.service.api.place.dto.PlaceDto;
import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class DiaLugarDto implements EntityDto {

    private static final long serialVersionUID = 5285317790107166186L;

    private Long id;

    private PlaceDto place;

    private int order;

    private Long time;

    private Long travelTime;

    private Long travelDistance;

    private String travelMode;

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
     * Returns the place
     *
     * @return The place
     */
    public PlaceDto getPlace() {
        return this.place;
    }

    /**
     * Sets the place to given value
     *
     * @param place
     *            The place to set
     */
    public void setPlace(PlaceDto place) {
        this.place = place;
    }

    /**
     * Returns the order
     *
     * @return The order
     */
    public int getOrder() {
        return this.order;
    }

    /**
     * Sets the order to given value
     *
     * @param order
     *            The order to set
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Returns the distance
     *
     * @return The distance
     */
    public Long getTravelDistance() {
        return this.travelDistance;
    }

    /**
     * Sets the distance to given value
     *
     * @param distance
     *            The distance to set
     */
    public void setTravelDistance(Long travelDistance) {
        this.travelDistance = travelDistance;
    }

    /**
     * Returns the time
     *
     * @return The time
     */
    public Long getTime() {
        return this.time;
    }

    /**
     * Sets the time to given value
     *
     * @param time
     *            The time to set
     */
    public void setTime(Long time) {
        this.time = time;
    }

    /**
     * Returns the travelMode
     *
     * @return The travelMode
     */
    public String getTravelMode() {
        return this.travelMode;
    }

    /**
     * Sets the travelMode to given value
     *
     * @param travelMode
     *            The travelMode to set
     */
    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    /**
     * Returns the travelTime
     * 
     * @return The travelTime
     */
    public Long getTravelTime() {
        return this.travelTime;
    }

    /**
     * Sets the travelTime to given value
     * 
     * @param travelTime
     *            The travelTime to set
     */
    public void setTravelTime(Long travelTime) {
        this.travelTime = travelTime;
    }

}
