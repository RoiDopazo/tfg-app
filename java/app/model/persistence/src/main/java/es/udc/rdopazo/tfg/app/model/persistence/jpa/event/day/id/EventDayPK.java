package es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EventDayPK implements Serializable {

    private static final long serialVersionUID = 4042676774750907069L;

    @Column(name = "EVENT_X_EVENT")
    private Long idEvent;

    @Column(name = "X_EVDAY")
    private Long idDay;

    public EventDayPK() {
    }

    public EventDayPK(Long id, Long size) {
        this.idEvent = id;
        this.idDay = size;
    }

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

}
