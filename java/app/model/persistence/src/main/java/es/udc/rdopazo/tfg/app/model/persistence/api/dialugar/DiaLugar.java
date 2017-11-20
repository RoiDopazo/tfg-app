package es.udc.rdopazo.tfg.app.model.persistence.api.dialugar;

import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;

public interface DiaLugar<D extends RouteDay, L extends Place> extends Entity<Long> {

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
     * Returns the day
     *
     * @return The day
     */
    D getDay();

    /**
     * Sets the day to given value
     *
     * @param day
     *            The day to set
     */
    void setDay(D day);

    /**
     * Returns the place
     *
     * @return The place
     */
    L getPlace();

    /**
     * Sets the place to given value
     *
     * @param place
     *            The place to set
     */
    void setPlace(L place);

    /**
     * Returns the order
     *
     * @return The order
     */
    int getOrder();

    /**
     * Sets the order to given value
     *
     * @param order
     *            The order to set
     */
    void setOrder(int order);

    /**
     * Returns the distance
     *
     * @return The distance
     */
    Long getTravelDistance();

    /**
     * Sets the distance to given value
     *
     * @param distance
     *            The distance to set
     */
    void setTravelDistance(Long travelDistance);

    /**
     * Returns the time
     *
     * @return The time
     */
    Long getTime();

    /**
     * Sets the time to given value
     *
     * @param time
     *            The time to set
     */
    void setTime(Long time);

    /**
     * Returns the travelMode
     *
     * @return The travelMode
     */
    String getTravelMode();

    /**
     * Sets the travelMode to given value
     *
     * @param travelMode
     *            The travelMode to set
     */
    void setTravelMode(String travelMode);

    /**
     * Returns the travelTime
     *
     * @return The travelTime
     */
    Long getTravelTime();

    /**
     * Sets the travelTime to given value
     *
     * @param travelTime
     *            The travelTime to set
     */
    void setTravelTime(Long travelTime);
}
