package com.epam.challenge.controller;

import com.epam.challenge.pojo.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler({MethodArgumentTypeMismatchException.class, IOException.class, JsonProcessingException.class})
    ResponseEntity<ApiResponse> handleValidationException(final Exception e) {
        log.warn("Validation error: '{}'", e.getMessage());
        return badRequest().body(ApiResponse.builder().status(BAD_REQUEST.toString()).message(e.getMessage()).build());
    }

    /**
     * Handle entity not found exception.
     */
    @ExceptionHandler({EntityNotFoundException.class})
    ResponseEntity<ApiResponse> handleEntityNotFoundExceptions(final Exception e) {
        log.warn("Entity not found error: '{}'", e.getMessage());
        return status(NOT_FOUND).body(ApiResponse.builder().status(NOT_FOUND.toString()).message(e.getMessage()).build());
    }

    /**
     * Handle unknown exceptions.
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiResponse> handleUnknownException(final Exception e) {
        log.error("Unknown exception", e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ApiResponse.builder().status(INTERNAL_SERVER_ERROR.toString()).message(e.getMessage()).build());
    }

    /**
     * Handle validation annotations on DTOs.
     */
    @SuppressWarnings("NullableProblems")
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String message = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(joining(", "));
        log.warn("Validation annotation error: '{}'", message);
        return badRequest().body(ApiResponse.builder().message(message).status(BAD_REQUEST.toString()).build());
    }

    /**
     * Handle illegal field values in incoming json request.
     */
    @SuppressWarnings("NullableProblems")
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        log.warn("Illegal argument error: '{}'", ex.getMessage());
        return badRequest().body(ApiResponse.builder().message(ex.getMessage()).status(BAD_REQUEST.toString()).build());
    }

}
