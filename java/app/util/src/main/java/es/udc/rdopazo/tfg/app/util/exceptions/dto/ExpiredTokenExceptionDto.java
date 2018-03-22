package es.udc.rdopazo.tfg.app.util.exceptions.dto;

public class ExpiredTokenExceptionDto {

    private String type;
    private String message;

    public ExpiredTokenExceptionDto(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the type
     *
     * @return The type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets the type to given value
     *
     * @param type
     *            The type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
