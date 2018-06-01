package es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.event.day.EventDay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.JpaEvent;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day.id.EventDayPK;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.place.JpaEventPlace;

@Entity
@Table(name = "EVENT_DAY")
public class JpaEventDay implements EventDay<JpaEvent, JpaEventPlace> {

    private static final long serialVersionUID = 3994906939374995020L;

    @EmbeddedId
    private EventDayPK dayPK;

    @Column(name = "DATE_")
    private Date date;

    @Column(name = "NUM_EV_PLACES")
    private Integer numEvPlaces;

    @JoinColumn(name = "EVENT_X_EVENT", referencedColumnName = "X_EVENT", insertable = false, updatable = false)
    @ManyToOne
    private JpaEvent event;

    @OneToMany(mappedBy = "day", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.REMOVE)
    @OrderBy("startHour, endHour")
    private List<JpaEventPlace> eventPlaces;

    /**
     * Returns the eventDayPK
     *
     * @return The eventDayPK
     */
    public EventDayPK getDayPK() {
        return this.dayPK;
    }

    /**
     * Sets the eventDayPK to given value
     *
     * @param eventDayPK
     *            The eventDayPK to set
     */
    public void setDayPK(EventDayPK eventDayPK) {
        this.dayPK = eventDayPK;
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
    public JpaEvent getEvent() {
        return this.event;
    }

    /**
     * Sets the event to given value
     *
     * @param event
     *            The event to set
     */
    public void setEvent(JpaEvent event) {
        this.event = event;
    }

    /**
     * Returns the eventPlaces
     *
     * @return The eventPlaces
     */
    public List<JpaEventPlace> getEventPlaces() {
        return this.eventPlaces;
    }

    /**
     * Sets the eventPlaces to given value
     *
     * @param eventPlaces
     *            The eventPlaces to set
     */
    public void setEventPlaces(List<JpaEventPlace> eventPlaces) {
        this.eventPlaces = eventPlaces;
    }

}
