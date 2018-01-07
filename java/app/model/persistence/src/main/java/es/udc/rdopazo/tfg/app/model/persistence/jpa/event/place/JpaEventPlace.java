package es.udc.rdopazo.tfg.app.model.persistence.jpa.event.place;

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

import es.udc.rdopazo.tfg.app.model.persistence.api.event.place.EventPlace;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.event.day.JpaEventDay;

@Entity
@Table(name = "EVENT_PLACE")
@SequenceGenerator(name = "event_place_seq", sequenceName = "EVENT_PLACE_SEQ", allocationSize = 1)
public class JpaEventPlace implements EventPlace<JpaEventDay> {

    private static final long serialVersionUID = 3623636156687716063L;

    @Id
    @Column(name = "X_EVEPL")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_place_seq")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "LAT")
    private Double lat;

    @Column(name = "LNG")
    private Double lng;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "START_HOUR")
    private Long startHour;

    @Column(name = "END_HOUR")
    private Long endHour;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "EVENT_X_EVENT", referencedColumnName = "EVENT_X_EVENT"),
            @JoinColumn(name = "EVDAY_X_EVDAY", referencedColumnName = "X_EVDAY") })
    private JpaEventDay day;

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
     * Returns the name
     *
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name to given value
     *
     * @param name
     *            The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description
     *
     * @return The description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description to given value
     *
     * @param description
     *            The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the lat
     *
     * @return The lat
     */
    public Double getLat() {
        return this.lat;
    }

    /**
     * Sets the lat to given value
     *
     * @param lat
     *            The lat to set
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * Returns the lng
     *
     * @return The lng
     */
    public Double getLng() {
        return this.lng;
    }

    /**
     * Sets the lng to given value
     *
     * @param lng
     *            The lng to set
     */
    public void setLng(Double lng) {
        this.lng = lng;
    }

    /**
     * Returns the address
     *
     * @return The address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets the address to given value
     *
     * @param address
     *            The address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the startDate
     *
     * @return The startDate
     */
    public Long getStartHour() {
        return this.startHour;
    }

    /**
     * Sets the startDate to given value
     *
     * @param startDate
     *            The startDate to set
     */
    public void setStartHour(Long startHour) {
        this.startHour = startHour;
    }

    /**
     * Returns the endDate
     *
     * @return The endDate
     */
    public Long getEndHour() {
        return this.endHour;
    }

    /**
     * Sets the endDate to given value
     *
     * @param endDate
     *            The endDate to set
     */
    public void setEndHour(Long endHour) {
        this.endHour = endHour;
    }

    /**
     * Returns the day
     *
     * @return The day
     */
    public JpaEventDay getDay() {
        return this.day;
    }

    /**
     * Sets the day to given value
     *
     * @param day
     *            The day to set
     */
    public void setDay(JpaEventDay day) {
        this.day = day;
    }

}
