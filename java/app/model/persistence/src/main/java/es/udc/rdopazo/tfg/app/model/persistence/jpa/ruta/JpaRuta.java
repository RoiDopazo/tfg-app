package es.udc.rdopazo.tfg.app.model.persistence.jpa.ruta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;

@Entity
@Table(name = "ROUTE")
@SequenceGenerator(name = "route_seq", sequenceName = "ROUTE_SEQ", allocationSize = 1)
public class JpaRuta implements Ruta {

    private static final long serialVersionUID = -7273214408089284378L;

    @Id
    @Column(name = "X_ROUTE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_seq")
    private Long id;

    @Column(name = "STATE")
    private String estado;

    @Column(name = "NUM_PLACES")
    private Long numero_lugares;

    @Column(name = "CITY")
    private String ciudad;

    @Column(name = "DISTANCE")
    private Long distancia;

    @Column(name = "TIME")
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
