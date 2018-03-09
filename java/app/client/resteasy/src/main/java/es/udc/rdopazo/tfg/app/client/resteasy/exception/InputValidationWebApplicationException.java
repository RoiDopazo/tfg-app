package es.udc.rdopazo.tfg.app.client.resteasy.exception;

import javax.ws.rs.WebApplicationException;

public class InputValidationWebApplicationException extends WebApplicationException {

    private static final long serialVersionUID = 1L;
    private String message;

    public InputValidationWebApplicationException(String message) {
        this.message = message;
    }

    /**
     * Returns the message
     *
     * @return The message
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the message to given value
     *
     * @param message
     *            The message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
