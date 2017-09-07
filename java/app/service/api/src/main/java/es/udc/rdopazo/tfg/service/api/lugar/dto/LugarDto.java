package es.udc.rdopazo.tfg.service.api.lugar.dto;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class LugarDto implements EntityDto {

    private static final long serialVersionUID = 2165236292757330644L;

    private Long id;

    private String nombre;

    private Long lat;

    private Long lng;

    private String direccion;

    private String cc;

    private String ciudad;

    private String pais;

    private String codigo_postal;

    private String provincia;

    private String email;

    private String facebook;

    private String telefono;

    private String twitter;

    private String id_foursquare;

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
