package es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar;

import java.io.Serializable;

import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;

public interface RutaLugar<L extends Lugar> extends Serializable {

    /**
     * Returns the lugar
     *
     * @return The lugar
     */
    L getLugar();

    /**
     * Returns the orden
     *
     * @return The orden
     */
    int getOrden();

    /**
     * Returns the distancia
     *
     * @return The distancia
     */
    Long getDistancia();

    /**
     * Returns the tiempo
     *
     * @return The tiempo
     */
    Long getTiempo();

    /**
     * Returns the modo_viaje
     *
     * @return The modo_viaje
     */
    String getModo_viaje();

    /**
     * Sets the lugar to given value
     *
     * @param lugar
     *            The lugar to set
     */
    void setLugar(L lugar);

    /**
     * Sets the orden to given value
     *
     * @param orden
     *            The orden to set
     */
    void setOrden(int orden);

    /**
     * Sets the distancia to given value
     *
     * @param distancia
     *            The distancia to set
     */
    void setDistancia(Long distancia);

    /**
     * Sets the tiempo to given value
     *
     * @param tiempo
     *            The tiempo to set
     */
    void setTiempo(Long tiempo);

    /**
     * Sets the modo_viaje to given value
     *
     * @param modo_viaje
     *            The modo_viaje to set
     */
    void setModo_viaje(String modo_viaje);
}
