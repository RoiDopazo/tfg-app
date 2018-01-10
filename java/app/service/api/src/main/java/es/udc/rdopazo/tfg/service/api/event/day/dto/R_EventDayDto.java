package es.udc.rdopazo.tfg.service.api.event.day.dto;

import java.util.Date;

import es.udc.rdopazo.tfg.service.api.event.dto.EventDto;
import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class R_EventDayDto implements EntityDto {

    private Long idEvent;

    private Long idDay;

    private Date date;

    private Integer numEvPlaces;

    private EventDto event;

    /**
     * Returns the idEvent
     * 
     * @return The idEvent
     */
    public Long getIdEvent() {
        return this.idEvent;
    }

    /**
     * Sets the idEvent to given value
     * 
     * @param idEvent
     *            The idEvent to set
     */
    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
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
     * Returns the date
     * 
     * @return The date
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets the date to given value
     * 
     * @param date
     *            The date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Returns the numEvPlaces
     * 
     * @return The numEvPlaces
     */
    public Integer getNumEvPlaces() {
        return this.numEvPlaces;
    }

    /**
     * Sets the numEvPlaces to given value
     * 
     * @param numEvPlaces
     *            The numEvPlaces to set
     */
    public void setNumEvPlaces(Integer numEvPlaces) {
        this.numEvPlaces = numEvPlaces;
    }

    /**
     * Returns the event
     * 
     * @return The event
     */
    public EventDto getEvent() {
        return this.event;
    }

    /**
     * Sets the event to given value
     * 
     * @param event
     *            The event to set
     */
    public void setEvent(EventDto event) {
        this.event = event;
    }

}
