package es.udc.rdopazo.tfg.service.api.usuario.dto;

import es.udc.rdopazo.tfg.service.api.util.EntityDto;

public class UsuarioDto implements EntityDto {

    private static final long serialVersionUID = -4041247271807880014L;

    private Long id;

    private String nombre;

    private String password;

    private String email;

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

}
