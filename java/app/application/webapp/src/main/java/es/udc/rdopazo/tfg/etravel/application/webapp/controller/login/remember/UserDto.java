package es.udc.rdopazo.tfg.etravel.application.webapp.controller.login.remember;

public class UserDto {

    private String username;

    private String password;

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

}
