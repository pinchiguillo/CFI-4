package com.publicaciones.utils;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    // Valida que el formato del email sea correcto
    public static boolean validar(String email) {
        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).find();
    }
}
