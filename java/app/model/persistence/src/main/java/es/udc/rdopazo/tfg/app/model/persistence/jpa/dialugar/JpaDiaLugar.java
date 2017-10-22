package es.udc.rdopazo.tfg.app.model.persistence.jpa.dialugar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.dialugar.DiaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.dia.JpaDia;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.lugar.JpaLugar;

@Entity
@Table(name = "DAY_PLACE")
@SequenceGenerator(name = "day_place_seq", sequenceName = "DAY_PLACE_SEQ", allocationSize = 1)
public class JpaDiaLugar implements DiaLugar<JpaDia, JpaLugar> {

    @Id
    @Column(name = "X_DAYPL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "day_place_seq")
    private Long id;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "ROUTE_X_ROUTE", referencedColumnName = "ROUTE_X_ROUTE"),
            @JoinColumn(name = "RDAY_X_RDAY", referencedColumnName = "X_RDAY") })
    private JpaDia day;

    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "PLACE_X_PLACE")
    private JpaLugar place;

    @Column(name = "ORDER_")
    private int order;

    @Column(name = "DISTANCE")
    private Long distance;

    @Column(name = "TIME_")
    private Long time;

    @Column(name = "TRAVEL_MODE")
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
     * Returns the day
     *
     * @return The day
     */
    public JpaDia getDay() {
        return this.day;
    }

    /**
     * Sets the day to given value
     *
     * @param day
     *            The day to set
     */
    public void setDay(JpaDia day) {
        this.day = day;
    }

    /**
     * Returns the place
     *
     * @return The place
     */
    public JpaLugar getPlace() {
        return this.place;
    }

    /**
     * Sets the place to given value
     *
     * @param place
     *            The place to set
     */
    public void setPlace(JpaLugar place) {
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
