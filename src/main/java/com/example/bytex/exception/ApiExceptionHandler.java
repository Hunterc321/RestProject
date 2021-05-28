package com.example.bytex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class,})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e)
    {
        HttpStatus request = e.getHttpStatus();
        ApiException apiException = new ApiException(e.getMessage(), e, request, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, request);
    }

    @ExceptionHandler(value = { HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleApiRequestException(HttpMessageNotReadableException e)
    {
        ApiException apiException = new ApiException(e.getMessage(), e, HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
