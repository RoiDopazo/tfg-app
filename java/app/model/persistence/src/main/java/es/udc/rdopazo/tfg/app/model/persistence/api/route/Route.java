package es.udc.rdopazo.tfg.app.model.persistence.api.route;

import java.util.Date;
import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.route.day.RouteDay;
import es.udc.rdopazo.tfg.app.model.persistence.api.usuario.Usuario;
import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;
import es.udc.rdopazo.tfg.app.util.exceptions.enums.RouteState;

public interface Route<D extends RouteDay<?>, U extends Usuario> extends Entity<Long> {

    /**
     * Returns the id
     *
     * @return The id
     */
    Long getId();

    /**
     * Sets the id to given value
     *
     * @param id
     *            The id to set
     */
    void setId(Long id);

    /**
     * Returns the photo
     *
     * @return The photo
     */
    String getPhoto();

    /**
     * Sets the photo to given value
     *
     * @param photo
     *            The photo to set
     */
    void setPhoto(String photo);

    /**
     * Returns the lat
     *
     * @return The lat
     */
    public Double getLat();

    /**
     * Sets the lat to given value
     *
     * @param lat
     *            The lat to set
     */
    public void setLat(Double lat);

    /**
     * Returns the lng
     *
     * @return The lng
     */
    public Double getLng();

    /**
     * Sets the lng to given value
     *
     * @param lng
     *            The lng to set
     */
    public void setLng(Double lng);

    /**
     * Returns the city
     *
     * @return The city
     */
    String getCity();

    /**
     * Sets the city to given value
     *
     * @param city
     *            The city to set
     */
    void setCity(String city);

    /**
     * Returns the country
     *
     * @return The country
     */
    String getCountry();

    /**
     * Sets the country to given value
     *
     * @param country
     *            The country to set
     */
    void setCountry(String country);

    /**
     * Returns the state
     *
     * @return The state
     */
    RouteState getState();

    /**
     * Sets the state to given value
     *
     * @param state
     *            The state to set
     */
    void setState(RouteState state);

    /**
     * Returns the creationDate
     *
     * @return The creationDate
     */
    Date getCreationDate();

    /**
     * Sets the creationDate to given value
     *
     * @param creationDate
     *            The creationDate to set
     */
    void setCreationDate(Date creationDate);

    /**
     * Returns the startDate
     *
     * @return The startDate
     */
    Date getStartDate();

    /**
     * Sets the startDate to given value
     *
     * @param startDate
     *            The startDate to set
     */
    void setStartDate(Date startDate);

    /**
     * Returns the endDate
     *
     * @return The endDate
     */
    Date getEndDate();

    /**
     * Sets the endDate to given value
     *
     * @param endDate
     *            The endDate to set
     */
    void setEndDate(Date endDate);

    /**
     * Returns the numDays
     *
     * @return The numDays
     */
    Integer getNumDays();

    /**
     * Sets the numDays to given value
     *
     * @param numDays
     *            The numDays to set
     */
    void setNumDays(Integer numDays);

    /**
     * Returns the numPlaces
     *
     * @return The numPlaces
     */
    Integer getNumPlaces();

    /**
     * Sets the numPlaces to given value
     *
     * @param numPlaces
     *            The numPlaces to set
     */
    void setNumPlaces(Integer numPlaces);

    /**
     * Returns the distance
     *
     * @return The distance
     */
    Long getDistance();

    /**
     * Sets the distance to given value
     *
     * @param distance
     *            The distance to set
     */
    void setDistance(Long distance);

    /**
     * Returns the time
     *
     * @return The time
     */
    Long getTime();

    /**
     * Sets the time to given value
     *
     * @param time
     *            The time to set
     */
    void setTime(Long time);

    /**
     * Returns the priv
     *
     * @return The priv
     */
    public boolean isPriv();

    /**
     * Sets the priv to given value
     *
     * @param priv
     *            The priv to set
     */
    public void setPriv(boolean priv);

    /**
     * Returns the user
     *
     * @return The user
     */
    U getUser();

    /**
     * Sets the user to given value
     *
     * @param user
     *            The user to set
     */
    void setUser(U user);

    /**
     * Returns the days
     *
     * @return The days
     */
    List<D> getDays();

    /**
     * Sets the days to given value
     *
     * @param days
     *            The days to set
     */
    void setDays(List<D> days);

    void addDay(D day);

}
