package com.agvsistemas.crudclientes.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{

    private final List<ValidationFieldError> errors = new ArrayList<>();

    public ValidationError(Integer statusCode, String message, String path) {
        super(statusCode, message, path);
    }

    public void addError(String fieldName, String message) {
        errors.add(new ValidationFieldError(fieldName, message));
    }

    public List<ValidationFieldError> getErrors() {
        return errors;
    }
}
