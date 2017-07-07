package es.udc.rdopazo.tfg.app.model.persistence.jpa.lugar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import es.udc.rdopazo.tfg.app.model.persistence.api.lugar.Lugar;

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

    @Column(name = "PSOTAL_CODE")
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

}
