package es.udc.rdopazo.tfg.app.model.persistence.api.visita;

import es.udc.rdopazo.tfg.app.model.persistence.api.dia.Dia;
import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;

public interface Visita<D extends Dia<?>, L extends Lugar> extends Entity<Long> {

    /**
     * Returns the id
     *
     * @return The id
     */
    public Long getId();

    /**
     * Sets the id to given value
     *
     * @param id
     *            The id to set
     */
    public void setId(Long id);

    /**
     * Returns the day
     *
     * @return The day
     */
    public D getDay();

    /**
     * Sets the day to given value
     *
     * @param day
     *            The day to set
     */
    public void setDay(D day);

    /**
     * Returns the order
     *
     * @return The order
     */
    public int getOrder();

    /**
     * Sets the order to given value
     *
     * @param order
     *            The order to set
     */
    public void setOrder(int order);

    /**
     * Returns the time
     *
     * @return The time
     */
    public Long getTime();

    /**
     * Sets the time to given value
     *
     * @param time
     *            The time to set
     */
    public void setTime(Long time);

    /**
     * Returns the travelTime
     *
     * @return The travelTime
     */
    public Long getTravelTime();

    /**
     * Sets the travelTime to given value
     *
     * @param travelTime
     *            The travelTime to set
     */
    public void setTravelTime(Long travelTime);

    /**
     * Returns the travelDistance
     *
     * @return The travelDistance
     */
    public Long getTravelDistance();

    /**
     * Sets the travelDistance to given value
     *
     * @param travelDistance
     *            The travelDistance to set
     */
    public void setTravelDistance(Long travelDistance);

    /**
     * Returns the travelMode
     *
     * @return The travelMode
     */
    public String getTravelMode();

    /**
     * Sets the travelMode to given value
     *
     * @param travelMode
     *            The travelMode to set
     */
    public void setTravelMode(String travelMode);

    /**
     * Returns the place
     *
     * @return The place
     */
    public L getPlace();

    /**
     * Sets the place to given value
     *
     * @param place
     *            The place to set
     */
    public void setPlace(L place);

}
