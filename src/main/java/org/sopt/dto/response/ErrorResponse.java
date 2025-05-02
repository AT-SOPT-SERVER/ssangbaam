package org.sopt.dto.response;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        String status,
        String message
) {
    public static ErrorResponse of(HttpStatus status, String message) {
        return new ErrorResponse(status.name(), message);
    }
}
