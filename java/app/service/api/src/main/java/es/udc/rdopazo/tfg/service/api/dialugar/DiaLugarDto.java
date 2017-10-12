package es.udc.rdopazo.tfg.service.api.dialugar;

import es.udc.rdopazo.tfg.service.api.lugar.dto.LugarDto;
import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class DiaLugarDto implements EntityDto {

    private Long id;

    private LugarDto place;

    private int order;

    private Long distance;

    private Long time;

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
    public LugarDto getPlace() {
        return this.place;
    }

    /**
     * Sets the place to given value
     *
     * @param place
     *            The place to set
     */
    public void setPlace(LugarDto place) {
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
    public Long getDistance() {
        return this.distance;
    }

    /**
     * Sets the distance to given value
     *
     * @param distance
     *            The distance to set
     */
    public void setDistance(Long distance) {
        this.distance = distance;
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

}
