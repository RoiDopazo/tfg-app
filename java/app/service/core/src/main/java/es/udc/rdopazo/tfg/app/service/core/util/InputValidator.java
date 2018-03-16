package es.udc.rdopazo.tfg.app.service.core.util;

import es.udc.rdopazo.tfg.app.util.exceptions.InputValidationException;

public class InputValidator {

    public static Integer validateIntegerNull(String att, String value) throws InputValidationException {

        if (!value.equals("null")) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new InputValidationException("Can not parse value of " + att + ": " + value);
            }
        }
        return null;
    }

    public static Long validateLongNull(String att, String value) throws InputValidationException {

        if (!value.equals("null")) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new InputValidationException("Can not parse value of " + att + ": " + value);
            }
        }
        return null;
    }

}