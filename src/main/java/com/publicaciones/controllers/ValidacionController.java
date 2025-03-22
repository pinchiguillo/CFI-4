package com.publicaciones.controllers;

import com.publicaciones.utils.EmailValidator;

public class ValidacionController {

    // Valida la sintaxis de un email utilizando EmailValidator
    public boolean validarEmail(String email) {
        return EmailValidator.validar(email);
    }
}
