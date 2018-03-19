package es.udc.rdopazo.tfg.app.model.persistence.api.stay;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.api.place.Place;
import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;

public interface Stay<D extends RouteDay<?>, L extends Place, EP extends EventPlace<?>> extends Entity<Long> {

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

    /**
     * Returns the travelDistance
     *
     * @return The travelDistance
     */
    Long getTravelDistance();

    /**
     * Sets the travelDistance to given value
     *
     * @param travelDistance
     *            The travelDistance to set
     */
    void setTravelDistance(Long travelDistance);

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
     * Returns the type
     * 
     * @return The type
     */
    String getType();

    /**
     * Sets the type to given value
     * 
     * @param type
     *            The type to set
     */
    void setType(String type);

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
     * Returns the event
     *
     * @return The event
     */
    EP getEventPlace();

    /**
     * Sets the event to given value
     *
     * @param event
     *            The event to set
     */
    void setEventPlace(EP eventPlace);

}
