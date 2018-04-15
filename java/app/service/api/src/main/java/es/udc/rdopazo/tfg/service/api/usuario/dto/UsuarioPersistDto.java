package es.udc.rdopazo.tfg.service.api.usuario.dto;

import java.util.Date;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class UsuarioPersistDto implements EntityDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Date creationDate;

    private String token;

    private String role;

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
     * Returns the username
     *
     * @return The username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username to given value
     *
     * @param username
     *            The username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password
     *
     * @return The password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the password to given value
     *
     * @param password
     *            The password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Returns the creationDate
     *
     * @return The creationDate
     */
    public Date getCreationDate() {
        return this.creationDate;
    }

    /**
     * Sets the creationDate to given value
     *
     * @param creationDate
     *            The creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Returns the role
     *
     * @return The role
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Sets the role to given value
     *
     * @param role
     *            The role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Returns the token
     * 
     * @return The token
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Sets the token to given value
     * 
     * @param token
     *            The token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

}
