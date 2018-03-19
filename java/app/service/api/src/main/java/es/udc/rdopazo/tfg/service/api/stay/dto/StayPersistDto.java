package es.udc.rdopazo.tfg.service.api.stay.dto;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class StayPersistDto implements EntityDto {

    private Long id;

    private Long idRoute;

    private Long idDay;

    private int order;

    private Long time;

    private Long travelTime;

    private Long travelDistance;

    private String travelMode;

    private String type;

    private Long idPlace;

    private Long idEventPlace;

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
     * Returns the idRoute
     *
     * @return The idRoute
     */
    public Long getIdRoute() {
        return this.idRoute;
    }

    /**
     * Sets the idRoute to given value
     *
     * @param idRoute
     *            The idRoute to set
     */
    public void setIdRoute(Long idRoute) {
        this.idRoute = idRoute;
    }

    /**
     * Returns the idDay
     *
     * @return The idDay
     */
    public Long getIdDay() {
        return this.idDay;
    }

    /**
     * Sets the idDay to given value
     *
     * @param idDay
     *            The idDay to set
     */
    public void setIdDay(Long idDay) {
        this.idDay = idDay;
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
     * Returns the idPlace
     *
     * @return The idPlace
     */
    public Long getIdPlace() {
        return this.idPlace;
    }

    /**
     * Sets the idPlace to given value
     *
     * @param idPlace
     *            The idPlace to set
     */
    public void setIdPlace(Long idPlace) {
        this.idPlace = idPlace;
    }

    /**
     * Returns the idEventPlace
     *
     * @return The idEventPlace
     */
    public Long getIdEventPlace() {
        return this.idEventPlace;
    }

    /**
     * Sets the idEventPlace to given value
     *
     * @param idEventPlace
     *            The idEventPlace to set
     */
    public void setIdEventPlace(Long idEventPlace) {
        this.idEventPlace = idEventPlace;
    }

    /**
     * Returns the type
     * 
     * @return The type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets the type to given value
     * 
     * @param type
     *            The type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
