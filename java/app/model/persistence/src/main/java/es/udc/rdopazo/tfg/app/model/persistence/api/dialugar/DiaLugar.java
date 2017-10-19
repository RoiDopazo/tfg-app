package es.udc.rdopazo.tfg.app.model.persistence.api.dialugar;

import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;

public interface DiaLugar<D extends Dia, L extends Lugar> extends Entity<Long> {

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
     * Returns the distance
     *
     * @return The distance
     */
    Long getDistance();

    /**
     * Sets the distance to given value
     *
     * @param distance
     *            The distance to set
     */
    void setDistance(Long distance);

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

}
