package es.udc.rdopazo.tfg.app.model.persistence.api.ruta;

import java.util.List;

import es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar.RutaLugar;
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
     * Returns the estado
     *
     * @return The estado
     */
    String getEstado();

    /**
     * Sets the estado to given value
     *
     * @param estado
     *            The estado to set
     */
    void setEstado(String estado);

    /**
     * Returns the numero_lugares
     *
     * @return The numero_lugares
     */
    Long getNumero_lugares();

    /**
     * Sets the numero_lugares to given value
     *
     * @param numero_lugares
     *            The numero_lugares to set
     */
    void setNumero_lugares(Long numero_lugares);

    /**
     * Returns the ciudad
     *
     * @return The ciudad
     */
    String getCiudad();

    /**
     * Sets the ciudad to given value
     *
     * @param ciudad
     *            The ciudad to set
     */
    void setCiudad(String ciudad);

    /**
     * Returns the distancia
     *
     * @return The distancia
     */
    Long getDistancia();

    /**
     * Sets the distancia to given value
     *
     * @param distancia
     *            The distancia to set
     */
    void setDistancia(Long distancia);

    /**
     * Returns the tiempo
     *
     * @return The tiempo
     */
    Long getTiempo();

    /**
     * Sets the tiempo to given value
     *
     * @param tiempo
     *            The tiempo to set
     */
    void setTiempo(Long tiempo);

    /**
     * Returns the ruta_lugares
     *
     * @return The ruta_lugares
     */
    List<RL> getRuta_lugares();

    /**
     * Sets the ruta_lugares to given value
     *
     * @param ruta_lugares
     *            The ruta_lugares to set
     */
    void setRuta_lugares(List<RL> ruta_lugares);

}
