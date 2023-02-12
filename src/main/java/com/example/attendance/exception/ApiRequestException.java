package com.example.attendance.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;


public class ApiRequestException extends ResponseStatusException {

    public ApiRequestException(HttpStatusCode status,
                               String reason,
                               Throwable cause) {
        super(status, reason, cause);
    }

    public ApiRequestException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
