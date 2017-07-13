package es.udc.rdopazo.tfg.app.api.ruta.dto;

import es.udc.rdopazo.tfg.app.api.util.EntityDto;

public class RutaDto implements EntityDto {

    private static final long serialVersionUID = 6751039845986605890L;

    private Long id;

    private String estado;

    private Long numero_lugares;

    private String ciudad;

    private Long distancia;

    private Long tiempo;

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
     * Returns the estado
     *
     * @return The estado
     */
    public String getEstado() {
        return this.estado;
    }

    /**
     * Sets the estado to given value
     *
     * @param estado
     *            The estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Returns the numero_lugares
     *
     * @return The numero_lugares
     */
    public Long getNumero_lugares() {
        return this.numero_lugares;
    }

    /**
     * Sets the numero_lugares to given value
     *
     * @param numero_lugares
     *            The numero_lugares to set
     */
    public void setNumero_lugares(Long numero_lugares) {
        this.numero_lugares = numero_lugares;
    }

    /**
     * Returns the ciudad
     *
     * @return The ciudad
     */
    public String getCiudad() {
        return this.ciudad;
    }

    /**
     * Sets the ciudad to given value
     *
     * @param ciudad
     *            The ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Returns the distancia
     *
     * @return The distancia
     */
    public Long getDistancia() {
        return this.distancia;
    }

    /**
     * Sets the distancia to given value
     *
     * @param distancia
     *            The distancia to set
     */
    public void setDistancia(Long distancia) {
        this.distancia = distancia;
    }

    /**
     * Returns the tiempo
     *
     * @return The tiempo
     */
    public Long getTiempo() {
        return this.tiempo;
    }

    /**
     * Sets the tiempo to given value
     *
     * @param tiempo
     *            The tiempo to set
     */
    public void setTiempo(Long tiempo) {
        this.tiempo = tiempo;
    }

}
