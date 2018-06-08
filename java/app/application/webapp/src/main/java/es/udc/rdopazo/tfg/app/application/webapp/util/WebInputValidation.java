package es.udc.rdopazo.tfg.app.application.webapp.util;

import java.util.Optional;

import es.udc.rdopazo.tfg.app.util.enums.Role;

public class WebInputValidation {

    public static String validateOptionalEmpty(Optional<String> value) {
        if (value.isPresent()) {
            return value.get();
        } else {
            return "";
        }
    }

    public static String validateOptionalNull(Optional<String> value) {
        if (value.isPresent()) {
            if (value.get().equals("")) {
                return "";
            }
            return value.get();
        }
        return "";
    }

    public static boolean validateRole(Role role1, String role2) {
        if (role1.toString().toUpperCase().equals(role2.toUpperCase())) {
            return true;
        } else {
            return false;
        }
    }
}
