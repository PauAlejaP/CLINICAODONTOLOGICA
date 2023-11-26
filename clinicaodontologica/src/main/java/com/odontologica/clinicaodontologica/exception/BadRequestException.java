package com.odontologica.clinicaodontologica.exception;

import org.springframework.validation.Errors;

public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }

    public Errors getBindingResult() {
        return null;
    }
}