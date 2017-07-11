package es.udc.rdopazo.tfg.app.model.persistence.jpa.lugar;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;
import es.udc.rdopazo.tfg.app.model.persistence.jpa.rutalugar.JpaRutaLugar;

@Entity
@Table(name = "PLACE")
@SequenceGenerator(name = "place_seq", sequenceName = "PLACE_SEQ", allocationSize = 1)
public class JpaLugar implements Lugar {

    private static final long serialVersionUID = 5215196298204512256L;

    @Id
    @Column(name = "X_PLACE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_seq")
    private Long id;

    @Column(name = "NAME")
    private String nombre;

    @Column(name = "LAT")
    private Long lat;

    @Column(name = "LNG")
    private Long lng;

    @Column(name = "ADDRESS")
    private String direccion;

    @Column(name = "CC")
    private String cc;

    @Column(name = "CITY")
    private String ciudad;

    @Column(name = "COUNTRY")
    private String pais;

    @Column(name = "POSTAL_CODE")
    private String codigo_postal;

    @Column(name = "PROVINCE")
    private String provincia;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FACEBOOK")
    private String facebook;

    @Column(name = "PHONE")
    private String telefono;

    @Column(name = "TWITTER")
    private String twitter;

    @Column(name = "ID_FOURSQUARE")
    private String id_foursquare;

    @Column(name = "VERIFIED")
    private int verificado;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "lugar")
    private List<JpaRutaLugar> ruta_luagres;

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
     * Returns the nombre
     *
     * @return The nombre
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * Sets the nombre to given value
     *
     * @param nombre
     *            The nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Returns the lat
     *
     * @return The lat
     */
    public Long getLat() {
        return this.lat;
    }

    /**
     * Sets the lat to given value
     *
     * @param lat
     *            The lat to set
     */
    public void setLat(Long lat) {
        this.lat = lat;
    }

    /**
     * Returns the lng
     *
     * @return The lng
     */
    public Long getLng() {
        return this.lng;
    }

    /**
     * Sets the lng to given value
     *
     * @param lng
     *            The lng to set
     */
    public void setLng(Long lng) {
        this.lng = lng;
    }

    /**
     * Returns the direccion
     *
     * @return The direccion
     */
    public String getDireccion() {
        return this.direccion;
    }

    /**
     * Sets the direccion to given value
     *
     * @param direccion
     *            The direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Returns the cc
     *
     * @return The cc
     */
    public String getCc() {
        return this.cc;
    }

    /**
     * Sets the cc to given value
     *
     * @param cc
     *            The cc to set
     */
    public void setCc(String cc) {
        this.cc = cc;
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
     * Returns the pais
     *
     * @return The pais
     */
    public String getPais() {
        return this.pais;
    }

    /**
     * Sets the pais to given value
     *
     * @param pais
     *            The pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Returns the codigo_postal
     *
     * @return The codigo_postal
     */
    public String getCodigo_postal() {
        return this.codigo_postal;
    }

    /**
     * Sets the codigo_postal to given value
     *
     * @param codigo_postal
     *            The codigo_postal to set
     */
    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    /**
     * Returns the provincia
     *
     * @return The provincia
     */
    public String getProvincia() {
        return this.provincia;
    }

    /**
     * Sets the provincia to given value
     *
     * @param provincia
     *            The provincia to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Returns the email
     *
     * @return The email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email to given value
     *
     * @param email
     *            The email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the facebook
     *
     * @return The facebook
     */
    public String getFacebook() {
        return this.facebook;
    }

    /**
     * Sets the facebook to given value
     *
     * @param facebook
     *            The facebook to set
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     * Returns the telefono
     *
     * @return The telefono
     */
    public String getTelefono() {
        return this.telefono;
    }

    /**
     * Sets the telefono to given value
     *
     * @param telefono
     *            The telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Returns the twitter
     *
     * @return The twitter
     */
    public String getTwitter() {
        return this.twitter;
    }

    /**
     * Sets the twitter to given value
     *
     * @param twitter
     *            The twitter to set
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    /**
     * Returns the id_foursquare
     *
     * @return The id_foursquare
     */
    public String getId_foursquare() {
        return this.id_foursquare;
    }

    /**
     * Sets the id_foursquare to given value
     *
     * @param id_foursquare
     *            The id_foursquare to set
     */
    public void setId_foursquare(String id_foursquare) {
        this.id_foursquare = id_foursquare;
    }

    /**
     * Returns the verificado
     *
     * @return The verificado
     */
    public int getVerificado() {
        return this.verificado;
    }

    /**
     * Sets the verificado to given value
     *
     * @param verificado
     *            The verificado to set
     */
    public void setVerificado(int verificado) {
        this.verificado = verificado;
    }

    /**
     * Returns the ruta_luagres
     *
     * @return The ruta_luagres
     */
    public List<JpaRutaLugar> getRuta_luagres() {
        return this.ruta_luagres;
    }

    /**
     * Sets the ruta_luagres to given value
     *
     * @param ruta_luagres
     *            The ruta_luagres to set
     */
    public void setRuta_luagres(List<JpaRutaLugar> ruta_luagres) {
        this.ruta_luagres = ruta_luagres;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.cc == null) ? 0 : this.cc.hashCode());
        result = (prime * result) + ((this.ciudad == null) ? 0 : this.ciudad.hashCode());
        result = (prime * result) + ((this.codigo_postal == null) ? 0 : this.codigo_postal.hashCode());
        result = (prime * result) + ((this.direccion == null) ? 0 : this.direccion.hashCode());
        result = (prime * result) + ((this.email == null) ? 0 : this.email.hashCode());
        result = (prime * result) + ((this.facebook == null) ? 0 : this.facebook.hashCode());
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        result = (prime * result) + ((this.id_foursquare == null) ? 0 : this.id_foursquare.hashCode());
        result = (prime * result) + ((this.lat == null) ? 0 : this.lat.hashCode());
        result = (prime * result) + ((this.lng == null) ? 0 : this.lng.hashCode());
        result = (prime * result) + ((this.nombre == null) ? 0 : this.nombre.hashCode());
        result = (prime * result) + ((this.pais == null) ? 0 : this.pais.hashCode());
        result = (prime * result) + ((this.provincia == null) ? 0 : this.provincia.hashCode());
        result = (prime * result) + ((this.ruta_luagres == null) ? 0 : this.ruta_luagres.hashCode());
        result = (prime * result) + ((this.telefono == null) ? 0 : this.telefono.hashCode());
        result = (prime * result) + ((this.twitter == null) ? 0 : this.twitter.hashCode());
        result = (prime * result) + this.verificado;
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
        JpaLugar other = (JpaLugar) obj;
        if (this.cc == null) {
            if (other.cc != null) {
                return false;
            }
        } else if (!this.cc.equals(other.cc)) {
            return false;
        }
        if (this.ciudad == null) {
            if (other.ciudad != null) {
                return false;
            }
        } else if (!this.ciudad.equals(other.ciudad)) {
            return false;
        }
        if (this.codigo_postal == null) {
            if (other.codigo_postal != null) {
                return false;
            }
        } else if (!this.codigo_postal.equals(other.codigo_postal)) {
            return false;
        }
        if (this.direccion == null) {
            if (other.direccion != null) {
                return false;
            }
        } else if (!this.direccion.equals(other.direccion)) {
            return false;
        }
        if (this.email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!this.email.equals(other.email)) {
            return false;
        }
        if (this.facebook == null) {
            if (other.facebook != null) {
                return false;
            }
        } else if (!this.facebook.equals(other.facebook)) {
            return false;
        }
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        if (this.id_foursquare == null) {
            if (other.id_foursquare != null) {
                return false;
            }
        } else if (!this.id_foursquare.equals(other.id_foursquare)) {
            return false;
        }
        if (this.lat == null) {
            if (other.lat != null) {
                return false;
            }
        } else if (!this.lat.equals(other.lat)) {
            return false;
        }
        if (this.lng == null) {
            if (other.lng != null) {
                return false;
            }
        } else if (!this.lng.equals(other.lng)) {
            return false;
        }
        if (this.nombre == null) {
            if (other.nombre != null) {
                return false;
            }
        } else if (!this.nombre.equals(other.nombre)) {
            return false;
        }
        if (this.pais == null) {
            if (other.pais != null) {
                return false;
            }
        } else if (!this.pais.equals(other.pais)) {
            return false;
        }
        if (this.provincia == null) {
            if (other.provincia != null) {
                return false;
            }
        } else if (!this.provincia.equals(other.provincia)) {
            return false;
        }
        if (this.ruta_luagres == null) {
            if (other.ruta_luagres != null) {
                return false;
            }
        } else if (!this.ruta_luagres.equals(other.ruta_luagres)) {
            return false;
        }
        if (this.telefono == null) {
            if (other.telefono != null) {
                return false;
            }
        } else if (!this.telefono.equals(other.telefono)) {
            return false;
        }
        if (this.twitter == null) {
            if (other.twitter != null) {
                return false;
            }
        } else if (!this.twitter.equals(other.twitter)) {
            return false;
        }
        if (this.verificado != other.verificado) {
            return false;
        }
        return true;
    }

}
