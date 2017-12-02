package es.udc.rdopazo.tfg.app.model.persistence.api.event.day;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.Event;
import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day.id.EventDayPK;

public interface EventDay<E extends Event<?>, EP extends EventPlace<?>> extends Serializable {

    /**
     * Returns the eventDayPK
     *
     * @return The eventDayPK
     */
    public EventDayPK getDayPK();

    /**
     * Sets the eventDayPK to given value
     *
     * @param eventDayPK
     *            The eventDayPK to set
     */
    public void setDayPK(EventDayPK eventDayPK);

    /**
     * Returns the date
     *
     * @return The date
     */
    public Date getDate();

    /**
     * Sets the date to given value
     *
     * @param date
     *            The date to set
     */
    public void setDate(Date date);

    /**
     * Returns the numEvPlaces
     *
     * @return The numEvPlaces
     */
    public Integer getNumEvPlaces();

    /**
     * Sets the numEvPlaces to given value
     *
     * @param numEvPlaces
     *            The numEvPlaces to set
     */
    public void setNumEvPlaces(Integer numEvPlaces);

    /**
     * Returns the eventPlaces
     *
     * @return The eventPlaces
     */
    public List<EP> getEventPlaces();

    /**
     * Sets the eventPlaces to given value
     *
     * @param eventPlaces
     *            The eventPlaces to set
     */
    public void setEventPlaces(List<EP> eventPlaces);

    /**
     * Returns the event
     *
     * @return The event
     */
    public E getEvent();

    /**
     * Sets the event to given value
     *
     * @param event
     *            The event to set
     */
    public void setEvent(E event);
}
