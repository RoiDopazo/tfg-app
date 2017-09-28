package es.udc.rdopazo.tfg.app.model.persistence.jpa.ruta;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.ruta.Ruta;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.rutalugar.JpaRutaLugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.usuario.JpaUsuario;

@Entity
@Table(name = "ROUTE")
@SequenceGenerator(name = "route_seq", sequenceName = "ROUTE_SEQ", allocationSize = 1)
public class JpaRuta implements Ruta<JpaRutaLugar> {

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

    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL)
    private List<JpaRutaLugar> ruta_lugares;

    @ManyToOne
    @JoinColumn(name = "USER_X_USER")
    private JpaUsuario usuario;

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

    /**
     * Returns the ruta_lugares
     *
     * @return The ruta_lugares
     */
    public List<JpaRutaLugar> getRuta_lugares() {
        return this.ruta_lugares;
    }

    /**
     * Sets the ruta_lugares to given value
     *
     * @param ruta_lugares
     *            The ruta_lugares to set
     */
    public void setRuta_lugares(List<JpaRutaLugar> ruta_lugares) {
        this.ruta_lugares = ruta_lugares;
    }

    /**
     * Returns the usuario
     * 
     * @return The usuario
     */
    public JpaUsuario getUsuario() {
        return this.usuario;
    }

    /**
     * Sets the usuario to given value
     * 
     * @param usuario
     *            The usuario to set
     */
    public void setUsuario(JpaUsuario usuario) {
        this.usuario = usuario;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.ciudad == null) ? 0 : this.ciudad.hashCode());
        result = (prime * result) + ((this.distancia == null) ? 0 : this.distancia.hashCode());
        result = (prime * result) + ((this.estado == null) ? 0 : this.estado.hashCode());
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        result = (prime * result) + ((this.numero_lugares == null) ? 0 : this.numero_lugares.hashCode());
        result = (prime * result) + ((this.ruta_lugares == null) ? 0 : this.ruta_lugares.hashCode());
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
        JpaRuta other = (JpaRuta) obj;
        if (this.ciudad == null) {
            if (other.ciudad != null) {
                return false;
            }
        } else if (!this.ciudad.equals(other.ciudad)) {
            return false;
        }
        if (this.distancia == null) {
            if (other.distancia != null) {
                return false;
            }
        } else if (!this.distancia.equals(other.distancia)) {
            return false;
        }
        if (this.estado == null) {
            if (other.estado != null) {
                return false;
            }
        } else if (!this.estado.equals(other.estado)) {
            return false;
        }
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.numero_lugares == null) {
            if (other.numero_lugares != null) {
                return false;
            }
        } else if (!this.numero_lugares.equals(other.numero_lugares)) {
            return false;
        }
        if (this.ruta_lugares == null) {
            if (other.ruta_lugares != null) {
                return false;
            }
        } else if (!this.ruta_lugares.equals(other.ruta_lugares)) {
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

}
