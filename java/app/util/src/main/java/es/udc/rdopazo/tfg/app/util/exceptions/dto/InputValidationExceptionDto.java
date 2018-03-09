package es.udc.rdopazo.tfg.app.util.exceptions.dto;

public class InputValidationExceptionDto {

    private String message;

    public InputValidationExceptionDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
