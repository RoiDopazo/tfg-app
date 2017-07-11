package es.udc.rdopazo.tfg.app.api.rutalugar.dto;

import es.udc.rdopazo.tfg.app.api.lugar.dto.LugarDto;

public class RutaLugarDto {

    private int orden;

    private Long distancia;

    private Long tiempo;

    private String modo_viaje;

    private LugarDto lugar;

    /**
     * Returns the orden
     * 
     * @return The orden
     */
    public int getOrden() {
        return this.orden;
    }

    /**
     * Sets the orden to given value
     * 
     * @param orden
     *            The orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
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

    /**
     * Returns the modo_viaje
     * 
     * @return The modo_viaje
     */
    public String getModo_viaje() {
        return this.modo_viaje;
    }

    /**
     * Sets the modo_viaje to given value
     * 
     * @param modo_viaje
     *            The modo_viaje to set
     */
    public void setModo_viaje(String modo_viaje) {
        this.modo_viaje = modo_viaje;
    }

    /**
     * Returns the lugar
     * 
     * @return The lugar
     */
    public LugarDto getLugar() {
        return this.lugar;
    }

    /**
     * Sets the lugar to given value
     * 
     * @param lugar
     *            The lugar to set
     */
    public void setLugar(LugarDto lugar) {
        this.lugar = lugar;
    }

}
