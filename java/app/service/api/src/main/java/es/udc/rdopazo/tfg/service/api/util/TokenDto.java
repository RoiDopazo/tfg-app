package es.udc.rdopazo.tfg.service.api.util;

import java.io.Serializable;

public class TokenDto implements Serializable {

    private String token;

    private String name;

    private String role;

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
     * Returns the name
     * 
     * @return The name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name to given value
     * 
     * @param name
     *            The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
