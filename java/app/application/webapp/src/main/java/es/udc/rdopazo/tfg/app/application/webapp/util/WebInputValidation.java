package es.udc.rdopazo.tfg.app.application.webapp.util;

import java.util.Optional;

public class WebInputValidation {

    public static String valideOptionalEmpty(Optional<String> value) {
        if (value.isPresent()) {
            return value.get();
        } else {
            return "";
        }
    }

    public static String valideOptionalNull(Optional<String> value) {
        if (value.isPresent()) {
            if (value.get().equals("")) {
                return "null";
            }
            return value.get();
        }
        return "null";
    }
}
