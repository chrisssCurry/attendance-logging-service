package com.example.attendanceloggingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<ApiExceptionResponse> handleApiRequestException(ApiRequestException exception) {
        HttpStatus exceptionCode = HttpStatus.valueOf(exception.getStatusCode().value());

        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                exception.getReason(),
                exceptionCode,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiExceptionResponse, exceptionCode);
    }
}
