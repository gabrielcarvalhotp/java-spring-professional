package com.agvsistemas.dscommerce.dto;

import java.time.Instant;

public class CustomError {

    private final Instant moment;
    private final Integer statusCode;
    private final String message;
    private final String path;

    public CustomError(Instant moment,
                       Integer statusCode,
                       String message,
                       String path) {
        this.moment = moment;
        this.statusCode = statusCode;
        this.message = message;
        this.path = path;
    }

    public CustomError(Integer statusCode,
                       String message,
                       String path) {
        this(Instant.now(), statusCode, message, path);
    }

    public Instant getMoment() {
        return moment;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
