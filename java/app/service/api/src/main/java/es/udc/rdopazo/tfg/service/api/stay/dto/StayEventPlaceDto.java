package es.udc.rdopazo.tfg.service.api.stay.dto;

import es.udc.rdopazo.tfg.service.api.event.place.dto.EventPlaceDto;
import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class StayEventPlaceDto implements EntityDto {

    private static final long serialVersionUID = -7876445741145810406L;

    private int order;

    private Long time;

    private Long travelTime;

    private Long travelDistance;

    private String travelMode;

    private EventPlaceDto eventPlace;

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
     * Returns the eventPlace
     *
     * @return The eventPlace
     */
    public EventPlaceDto getEventPlace() {
        return this.eventPlace;
    }

    /**
     * Sets the eventPlace to given value
     *
     * @param eventPlace
     *            The eventPlace to set
     */
    public void setEventPlace(EventPlaceDto eventPlace) {
        this.eventPlace = eventPlace;
    }

}
