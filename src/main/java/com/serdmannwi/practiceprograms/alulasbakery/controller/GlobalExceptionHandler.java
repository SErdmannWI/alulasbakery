package com.serdmannwi.practiceprograms.alulasbakery.controller;

import com.serdmannwi.practiceprograms.alulasbakery.exceptions.ErrorResponse;
import com.serdmannwi.practiceprograms.alulasbakery.exceptions.TestNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**----------------------------------------------- Test Controller ----------------------------------------------**/

    @ExceptionHandler(TestNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTestNotFoundException(TestNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
