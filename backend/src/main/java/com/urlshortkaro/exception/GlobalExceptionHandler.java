package com.urlshortkaro.exception;

import com.urlshortkaro.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Clock;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Clock clock;

    public GlobalExceptionHandler(Clock clock) {
        this.clock = clock;
    }

    @ExceptionHandler(InvalidUrlException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidUrlException(
            InvalidUrlException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                ex.getErrorCode().getCode(),
                ex.getErrorCode().getMessage(),
                LocalDateTime.now(clock)
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(ShortCodeGenerationException.class)
    public ResponseEntity<ApiErrorResponse> handleShortCodeGenerationException(
            ShortCodeGenerationException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                ex.getErrorCode().getCode(),
                ex.getErrorCode().getMessage(),
                LocalDateTime.now(clock)
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                "SYS001",
                "Something went wrong",
                LocalDateTime.now(clock)
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUrlNotFoundException(
            UrlNotFoundException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                ex.getErrorCode().getCode(),
                ex.getErrorCode().getMessage(),
                LocalDateTime.now(clock)
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(InvalidAliasException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidAliasException(
            InvalidAliasException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                ex.getErrorCode().getCode(),
                ex.getErrorCode().getMessage(),
                LocalDateTime.now(clock)
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(AliasAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleAliasAlreadyExistsException(
            AliasAlreadyExistsException ex) {

        ApiErrorResponse response = new ApiErrorResponse(
                ex.getErrorCode().getCode(),
                ex.getErrorCode().getMessage(),
                LocalDateTime.now(clock)
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }
}