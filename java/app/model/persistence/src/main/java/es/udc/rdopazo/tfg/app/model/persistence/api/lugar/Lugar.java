package es.udc.rdopazo.tfg.app.model.persistence.api.lugar;

import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;

public interface Lugar extends Entity<Long> {

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
     * Returns the nombre
     *
     * @return The nombre
     */
    String getNombre();

    /**
     * Sets the nombre to given value
     *
     * @param nombre
     *            The nombre to set
     */
    void setNombre(String nombre);

    /**
     * Returns the lat
     *
     * @return The lat
     */
    Long getLat();

    /**
     * Sets the lat to given value
     *
     * @param lat
     *            The lat to set
     */
    void setLat(Long lat);

    /**
     * Returns the lng
     *
     * @return The lng
     */
    Long getLng();

    /**
     * Sets the lng to given value
     *
     * @param lng
     *            The lng to set
     */
    void setLng(Long lng);

    /**
     * Returns the direccion
     *
     * @return The direccion
     */
    String getDireccion();

    /**
     * Sets the direccion to given value
     *
     * @param direccion
     *            The direccion to set
     */
    void setDireccion(String direccion);

    /**
     * Returns the cc
     *
     * @return The cc
     */
    String getCc();

    /**
     * Sets the cc to given value
     *
     * @param cc
     *            The cc to set
     */
    void setCc(String cc);

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
     * Returns the pais
     *
     * @return The pais
     */
    String getPais();

    /**
     * Sets the pais to given value
     *
     * @param pais
     *            The pais to set
     */
    void setPais(String pais);

    /**
     * Returns the codigo_postal
     *
     * @return The codigo_postal
     */
    String getCodigo_postal();

    /**
     * Sets the codigo_postal to given value
     *
     * @param codigo_postal
     *            The codigo_postal to set
     */
    void setCodigo_postal(String codigo_postal);

    /**
     * Returns the provincia
     *
     * @return The provincia
     */
    String getProvincia();

    /**
     * Sets the provincia to given value
     *
     * @param provincia
     *            The provincia to set
     */
    void setProvincia(String provincia);

    /**
     * Returns the email
     *
     * @return The email
     */
    String getEmail();

    /**
     * Sets the email to given value
     *
     * @param email
     *            The email to set
     */
    void setEmail(String email);

    /**
     * Returns the facebook
     *
     * @return The facebook
     */
    String getFacebook();

    /**
     * Sets the facebook to given value
     *
     * @param facebook
     *            The facebook to set
     */
    void setFacebook(String facebook);

    /**
     * Returns the telefono
     *
     * @return The telefono
     */
    String getTelefono();

    /**
     * Sets the telefono to given value
     *
     * @param telefono
     *            The telefono to set
     */
    void setTelefono(String telefono);

    /**
     * Returns the twitter
     *
     * @return The twitter
     */
    String getTwitter();

    /**
     * Sets the twitter to given value
     *
     * @param twitter
     *            The twitter to set
     */
    void setTwitter(String twitter);

    /**
     * Returns the id_foursquare
     *
     * @return The id_foursquare
     */
    String getId_foursquare();

    /**
     * Sets the id_foursquare to given value
     *
     * @param id_foursquare
     *            The id_foursquare to set
     */
    void setId_foursquare(String id_foursquare);

    /**
     * Returns the verificado
     *
     * @return The verificado
     */
    int getVerificado();

    /**
     * Sets the verificado to given value
     *
     * @param verificado
     *            The verificado to set
     */
    void setVerificado(int verificado);

}
