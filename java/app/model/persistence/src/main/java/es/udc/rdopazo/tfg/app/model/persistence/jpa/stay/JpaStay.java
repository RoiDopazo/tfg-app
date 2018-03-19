package es.udc.rdopazo.tfg.app.model.persistence.jpa.stay;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.stay.Stay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.place.JpaEventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.place.JpaPlace;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.JpaRouteDay;

@Entity
@Table(name = "STAY")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@SequenceGenerator(name = "stay_seq", sequenceName = "STAY_SEQ", allocationSize = 1)
public class JpaStay implements Stay<JpaRouteDay, JpaPlace, JpaEventPlace> {

    private static final long serialVersionUID = 4844994432498700198L;

    @Id
    @Column(name = "X_STAY")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stay_seq")
    private Long id;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "ROUTE_X_ROUTE", referencedColumnName = "ROUTE_X_ROUTE"),
            @JoinColumn(name = "RDAY_X_RDAY", referencedColumnName = "X_RDAY") })
    private JpaRouteDay day;

    @Column(name = "ORDER_")
    private int order;

    @Column(name = "TIME_")
    private Long time;

    @Column(name = "TRAVEL_TIME")
    private Long travelTime;

    @Column(name = "TRAVEL_DISTANCE")
    private Long travelDistance;

    @Column(name = "TRAVEL_MODE")
    private String travelMode;

    @Column(name = "TYPE", insertable = false, updatable = false)
    private String type;

    @ManyToOne()
    @JoinColumn(name = "PLACE_X_PLACE")
    private JpaPlace place;

    @ManyToOne()
    @JoinColumn(name = "EVEPL_X_EVEPL")
    private JpaEventPlace eventPlace;

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
     * Returns the day
     *
     * @return The day
     */
    public JpaRouteDay getDay() {
        return this.day;
    }

    /**
     * Sets the day to given value
     *
     * @param day
     *            The day to set
     */
    public void setDay(JpaRouteDay day) {
        this.day = day;
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

    /**
     * Returns the place
     *
     * @return The place
     */
    public JpaPlace getPlace() {
        return this.place;
    }

    /**
     * Sets the place to given value
     *
     * @param place
     *            The place to set
     */
    public void setPlace(JpaPlace place) {
        this.place = place;
    }

    /**
     * Returns the event
     *
     * @return The event
     */
    public JpaEventPlace getEventPlace() {
        return this.eventPlace;
    }

    /**
     * Sets the event to given value
     *
     * @param event
     *            The event to set
     */
    public void setEventPlace(JpaEventPlace eventPlace) {
        this.eventPlace = eventPlace;
    }

}
