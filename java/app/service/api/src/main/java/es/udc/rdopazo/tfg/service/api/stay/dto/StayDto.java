package es.udc.rdopazo.tfg.service.api.stay.dto;

import es.udc.rdopazo.tfg.service.api.place.dto.PlaceDto;
import es.udc.rdopazo.tfg.service.api.route.day.dto.RouteDayDto;
import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class StayDto implements EntityDto {

    private static final long serialVersionUID = 6445784412799408774L;

    private Long id;

    private RouteDayDto day;

    private int order;

    private Long time;

    private Long travelTime;

    private Long travelDistance;

    private String travelMode;

    private PlaceDto place;

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
     * Returns the day
     *
     * @return The day
     */
    public RouteDayDto getDay() {
        return this.day;
    }

    /**
     * Sets the day to given value
     *
     * @param day
     *            The day to set
     */
    public void setDay(RouteDayDto day) {
        this.day = day;
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

    /**
     * Returns the travelDistance
     *
     * @return The travelDistance
     */
    public Long getTravelDistance() {
        return this.travelDistance;
    }

    /**
     * Sets the travelDistance to given value
     *
     * @param travelDistance
     *            The travelDistance to set
     */
    public void setTravelDistance(Long travelDistance) {
        this.travelDistance = travelDistance;
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

}
