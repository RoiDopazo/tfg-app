package es.udc.rdopazo.tfg.app.model.persistence.jpa.rutalugar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.model.persistence.api.rutalugar.RutaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.lugar.JpaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.ruta.JpaRuta;

@Entity
@Table(name = "ROUTE_PLACE")
@SequenceGenerator(name = "route_place_seq", sequenceName = "ROUTE_PLACE_SEQ", allocationSize = 1)
public class JpaRutaLugar implements RutaLugar<JpaLugar> {

    private static final long serialVersionUID = -8676214584131307744L;

    @Id
    @Column(name = "X_RP")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_place_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ROUTE_X_ROUTE")
    private JpaRuta ruta;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PLACE_X_PLACE")
    private JpaLugar lugar;

    @Column(name = "ORDER_")
    private int orden;

    @Column(name = "DISTANCE")
    private Long distancia;

    @Column(name = "TIME_")
    private Long tiempo;

    @Column(name = "TRAVEL_MODE")
    private String modo_viaje;

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
     * Returns the ruta
     *
     * @return The ruta
     */
    public JpaRuta getRuta() {
        return this.ruta;
    }

    /**
     * Returns the lugar
     *
     * @return The lugar
     */
    public JpaLugar getLugar() {
        return this.lugar;
    }

    /**
     * Returns the orden
     *
     * @return The orden
     */
    public int getOrden() {
        return this.orden;
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
     * Returns the tiempo
     *
     * @return The tiempo
     */
    public Long getTiempo() {
        return this.tiempo;
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
     * Sets the ruta to given value
     *
     * @param ruta
     *            The ruta to set
     */
    public void setRuta(JpaRuta ruta) {
        this.ruta = ruta;
    }

    /**
     * Sets the lugar to given value
     *
     * @param lugar
     *            The lugar to set
     */
    public void setLugar(JpaLugar lugar) {
        this.lugar = lugar;
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
     * Sets the distancia to given value
     *
     * @param distancia
     *            The distancia to set
     */
    public void setDistancia(Long distancia) {
        this.distancia = distancia;
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
     * Sets the modo_viaje to given value
     *
     * @param modo_viaje
     *            The modo_viaje to set
     */
    public void setModo_viaje(String modo_viaje) {
        this.modo_viaje = modo_viaje;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.distancia == null) ? 0 : this.distancia.hashCode());
        result = (prime * result) + ((this.lugar == null) ? 0 : this.lugar.hashCode());
        result = (prime * result) + ((this.modo_viaje == null) ? 0 : this.modo_viaje.hashCode());
        result = (prime * result) + this.orden;
        result = (prime * result) + ((this.ruta == null) ? 0 : this.ruta.hashCode());
        result = (prime * result) + ((this.tiempo == null) ? 0 : this.tiempo.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        JpaRutaLugar other = (JpaRutaLugar) obj;
        if (this.distancia == null) {
            if (other.distancia != null) {
                return false;
            }
        } else if (!this.distancia.equals(other.distancia)) {
            return false;
        }
        if (this.lugar == null) {
            if (other.lugar != null) {
                return false;
            }
        } else if (!this.lugar.equals(other.lugar)) {
            return false;
        }
        if (this.modo_viaje == null) {
            if (other.modo_viaje != null) {
                return false;
            }
        } else if (!this.modo_viaje.equals(other.modo_viaje)) {
            return false;
        }
        if (this.orden != other.orden) {
            return false;
        }
        if (this.ruta == null) {
            if (other.ruta != null) {
                return false;
            }
        } else if (!this.ruta.equals(other.ruta)) {
            return false;
        }
        if (this.tiempo == null) {
            if (other.tiempo != null) {
                return false;
            }
        } else if (!this.tiempo.equals(other.tiempo)) {
            return false;
        }
        return true;
    }

    public void setRuta(Ruta<?> ruta) {
        this.ruta = (JpaRuta) ruta;

    }

}
