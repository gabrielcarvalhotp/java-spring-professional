package com.agvsistemas.dscommerce.dto;

import java.time.Instant;

public record CustomError(
        Instant moment,
        Integer statusCode,
        String message,
        String path
) {
    public CustomError(Integer statusCode,
                       String message,
                       String path) {
        this(Instant.now(), statusCode, message, path);
    }
}
