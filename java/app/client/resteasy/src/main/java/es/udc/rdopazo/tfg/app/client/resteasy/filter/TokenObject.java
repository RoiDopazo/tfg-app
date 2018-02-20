package es.udc.rdopazo.tfg.app.client.resteasy.filter;

import org.springframework.stereotype.Component;

@Component
public class TokenObject {

    private String token;

    public TokenObject() {

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
