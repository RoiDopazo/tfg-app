package es.udc.rdopazo.tfg.service.api.ruta.dto;

import es.udc.rdopazo.tfg.service.api.usuario.dto.UsuarioDto;
import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class RutaDto implements EntityDto {

    private static final long serialVersionUID = 6751039845986605890L;

    private Long id;

    private String state;

    private Long num_places;

    private String city;

    private Long distance;

    private Long time;

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
     * Returns the num_places
     * 
     * @return The num_places
     */
    public Long getNum_places() {
        return this.num_places;
    }

    /**
     * Sets the num_places to given value
     * 
     * @param num_places
     *            The num_places to set
     */
    public void setNum_places(Long num_places) {
        this.num_places = num_places;
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

}
