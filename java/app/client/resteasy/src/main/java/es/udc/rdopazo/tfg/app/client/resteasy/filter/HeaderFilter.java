package es.udc.rdopazo.tfg.app.client.resteasy.filter;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

public class HeaderFilter implements ClientRequestFilter {

    private String token;

    public void filter(ClientRequestContext requestContext) throws IOException {

        if (this.token != null) {
            requestContext.getHeaders().add("Authorization", "Bearer " + this.token);
        }

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
