package es.udc.rdopazo.tfg.app.util.exceptions.dto;

public class CustomErrorExceptionDto {

    private String message;

    public CustomErrorExceptionDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
