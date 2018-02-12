package es.udc.rdopazo.tfg.app.model.persistence.api.usuario;

import java.util.Date;

import es.udc.rdopazo.tfg.app.model.persistence.jpa.user.Role;
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
     * Returns the username
     *
     * @return The username
     */
    String getUsername();

    /**
     * Sets the username to given value
     *
     * @param username
     *            The username to set
     */
    void setUsername(String username);

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

    /**
     * Returns the creationDate
     *
     * @return The creationDate
     */
    public Date getCreationDate();

    /**
     * Sets the creationDate to given value
     *
     * @param creationDate
     *            The creationDate to set
     */
    public void setCreationDate(Date creationDate);

    /**
     * Returns the token
     *
     * @return The token
     */
    public String getToken();

    /**
     * Sets the token to given value
     *
     * @param token
     *            The token to set
     */
    public void setToken(String token);

    /**
     * Returns the role
     *
     * @return The role
     */
    public Role getRole();

    /**
     * Sets the role to given value
     *
     * @param role
     *            The role to set
     */
    public void setRole(Role role);
}
