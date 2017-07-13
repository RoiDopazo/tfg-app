package es.udc.rdopazo.tfg.app.model.persistence.api.usuario;

import es.udc.rdopazo.tfg.app.model.persistence.util.Entity;

public interface Usuario extends Entity<Long> {

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
     * Returns the password
     * 
     * @return The password
     */
    String getPassword();

    /**
     * Sets the password to given value
     * 
     * @param password
     *            The password to set
     */
    void setPassword(String password);

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
}
