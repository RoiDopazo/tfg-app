package es.udc.rdopazo.tfg.app.util.exceptions;

@SuppressWarnings("serial")
public class CustomErrorException extends Exception {

    public CustomErrorException(String message) {
        super(message);
    }
}
