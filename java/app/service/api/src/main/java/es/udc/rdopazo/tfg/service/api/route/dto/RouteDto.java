package es.udc.rdopazo.tfg.service.api.route.dto;

import java.util.Date;
import java.util.List;

import es.udc.rdopazo.tfg.service.api.route.day.dto.RouteDayDto;
import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioDto;
import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class RouteDto implements EntityDto {

    private static final long serialVersionUID = 6751039845986605890L;

    private Long id;

    private String name;

    private String photo;

    private String city;

    private String country;

    private String state;

    private Date creationDate;

    private Date startDate;

    private Date endDate;

    private Integer numDays;

    private Integer numPlaces;

    private Long distance;

    private Long time;

    private List<RouteDayDto> days;

    private UsuarioDto user;

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
    public String getState() {
        return this.state;
    }

    /**
     * Sets the state to given value
     *
     * @param state
     *            The state to set
     */
    public void setState(String state) {
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
     * Returns the user
     *
     * @return The user
     */
    public UsuarioDto getUser() {
        return this.user;
    }

    /**
     * Sets the user to given value
     *
     * @param user
     *            The user to set
     */
    public void setUser(UsuarioDto user) {
        this.user = user;
    }

    /**
     * Returns the days
     *
     * @return The days
     */
    public List<RouteDayDto> getDays() {
        return this.days;
    }

    /**
     * Sets the days to given value
     *
     * @param days
     *            The days to set
     */
    public void setDays(List<RouteDayDto> days) {
        this.days = days;
    }

}
