package com.odontologica.clinicaodontologica.exception;

import org.springframework.validation.Errors;

public class DataInvalidException extends Exception {

    public DataInvalidException(String message) {
        super(message);
    }

    public Errors getBindingResult() {
        return null;
    }
}
