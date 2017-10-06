package es.udc.rdopazo.tfg.app.model.persistence.api.ruta;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar.RutaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.rutalugar.JpaRutaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.usuario.JpaUsuario;
import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;

public interface Ruta<RL extends RutaLugar<?>> extends Entity<Long> {

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
     * Returns the state
     *
     * @return The state
     */
    String getState();

    /**
     * Sets the state to given value
     *
     * @param state
     *            The state to set
     */
    void setState(String state);

    /**
     * Returns the num_places
     *
     * @return The num_places
     */
    Long getNum_places();

    /**
     * Sets the num_places to given value
     *
     * @param num_places
     *            The num_places to set
     */
    void setNum_places(Long num_places);

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
     * Returns the ruta_lugares
     *
     * @return The ruta_lugares
     */
    List<JpaRutaLugar> getRuta_lugares();

    /**
     * Sets the ruta_lugares to given value
     *
     * @param ruta_lugares
     *            The ruta_lugares to set
     */
    void setRuta_lugares(List<JpaRutaLugar> ruta_lugares);

    /**
     * Returns the user
     *
     * @return The user
     */
    JpaUsuario getUser();

    /**
     * Sets the user to given value
     *
     * @param user
     *            The user to set
     */
    void setUser(JpaUsuario user);

}
