package com.ichwan.restful.controller;

import com.ichwan.restful.model.response.WebResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;

/**
 * Annotation @RestControllerAdvice digunakan untuk menangani exception di seluruh aplikasi
 */
@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<WebResponse<String>> constraintViolationException(ConstraintViolationException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(WebResponse.<String>builder().error(exception.getMessage()).build());
    }

    /**
     * Class ResponseStatusException merupakan alternatif dari annotation @ResponseStatus yang hanya memiliki
     * dua constructor yakni status code dan reason message
     * @Params statusCode: Integer, reason: String, cause: throw
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<String>> apiException(ResponseStatusException exception){
        return ResponseEntity.status(exception.getRawStatusCode())
                .body(WebResponse.<String>builder().error(exception.getReason()).build());
    }
}
