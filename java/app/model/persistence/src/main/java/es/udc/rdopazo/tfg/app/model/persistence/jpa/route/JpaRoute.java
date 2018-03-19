package es.udc.rdopazo.tfg.app.model.persistence.jpa.route;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.Route;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.JpaRouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.route.day.id.RouteDayPK;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.user.JpaUser;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.RouteState;

@Entity
@Table(name = "ROUTE")
@SequenceGenerator(name = "route_seq", sequenceName = "ROUTE_SEQ", allocationSize = 1)
public class JpaRoute implements Route<JpaRouteDay, JpaUser> {

    private static final long serialVersionUID = -7273214408089284378L;

    @Id
    @Column(name = "X_ROUTE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_seq")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHOTO")
    private String photo;

    @Column(name = "LAT")
    private Double lat;

    @Column(name = "LNG")
    private Double lng;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private RouteState state;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "NUM_DAYS")
    private Integer numDays;

    @Column(name = "NUM_PLACES")
    private Integer numPlaces;

    @Column(name = "DISTANCE")
    private Long distance;

    @Column(name = "TIME")
    private Long time;

    @Column(name = "PRIVATE")
    private boolean priv;

    @ManyToOne
    @JoinColumn(name = "USER_X_USER")
    private JpaUser user;

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.REMOVE)
    @OrderBy("diaPK.idDay ASC")
    private List<JpaRouteDay> days = new ArrayList<JpaRouteDay>();

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
     * Returns the photo
     *
     * @return The photo
     */
    public String getPhoto() {
        return this.photo;
    }

    /**
     * Sets the photo to given value
     *
     * @param photo
     *            The photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
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
     * Returns the city
     *
     * @return The city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Sets the city to given value
     *
     * @param city
     *            The city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the country
     *
     * @return The country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Sets the country to given value
     *
     * @param country
     *            The country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the state
     *
     * @return The state
     */
    public RouteState getState() {
        return this.state;
    }

    /**
     * Sets the state to given value
     *
     * @param state
     *            The state to set
     */
    public void setState(RouteState state) {
        this.state = state;
    }

    /**
     * Returns the creationDate
     *
     * @return The creationDate
     */
    public Date getCreationDate() {
        return this.creationDate;
    }

    /**
     * Sets the creationDate to given value
     *
     * @param creationDate
     *            The creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Returns the startDate
     *
     * @return The startDate
     */
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * Sets the startDate to given value
     *
     * @param startDate
     *            The startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Returns the endDate
     *
     * @return The endDate
     */
    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * Sets the endDate to given value
     *
     * @param endDate
     *            The endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Returns the numDays
     *
     * @return The numDays
     */
    public Integer getNumDays() {
        return this.numDays;
    }

    /**
     * Sets the numDays to given value
     *
     * @param numDays
     *            The numDays to set
     */
    public void setNumDays(Integer numDays) {
        this.numDays = numDays;
    }

    /**
     * Returns the numPlaces
     *
     * @return The numPlaces
     */
    public Integer getNumPlaces() {
        return this.numPlaces;
    }

    /**
     * Sets the numPlaces to given value
     *
     * @param numPlaces
     *            The numPlaces to set
     */
    public void setNumPlaces(Integer numPlaces) {
        this.numPlaces = numPlaces;
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
     * Returns the priv
     *
     * @return The priv
     */
    public boolean isPriv() {
        return this.priv;
    }

    /**
     * Sets the priv to given value
     *
     * @param priv
     *            The priv to set
     */
    public void setPriv(boolean priv) {
        this.priv = priv;
    }

    /**
     * Returns the user
     *
     * @return The user
     */
    public JpaUser getUser() {
        return this.user;
    }

    /**
     * Sets the user to given value
     *
     * @param user
     *            The user to set
     */
    public void setUser(JpaUser user) {
        this.user = user;
    }

    /**
     * Returns the days
     *
     * @return The days
     */
    public List<JpaRouteDay> getDays() {
        return this.days;
    }

    /**
     * Sets the days to given value
     *
     * @param days
     *            The days to set
     */
    public void setDays(List<JpaRouteDay> days) {
        this.days = days;
    }

    public void addDay(JpaRouteDay day) {

        this.days.add(day);
        day.setDiaPK(new RouteDayPK(this.id, (long) this.days.size()));
    }

}
