package com.example.emlakburada.advice;

import com.example.emlakburada.dto.response.AdvertResponse;
import com.example.emlakburada.dto.response.ErrorResponse;
import com.example.emlakburada.exception.AdvertNotFoundException;
import com.example.emlakburada.exception.DatabaseConnectionException;
import com.example.emlakburada.exception.EmlakBuradaException;
import com.example.emlakburada.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(UserNotFoundException exception) {
        log.error("User not found exception occurred." + exception.getMessage());
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdvertNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(AdvertNotFoundException exception) {
        log.error("Advert not found exception occurred." + exception.getMessage());
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DatabaseConnectionException.class)
    public ResponseEntity<ErrorResponse> handle(DatabaseConnectionException exception) {
        log.error("Database Connection exception occurred." + exception.getMessage());
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmlakBuradaException.class)
    public ResponseEntity<ErrorResponse> handle(EmlakBuradaException exception) {
        log.error("General EmlakBurada exception occurred." + exception.getMessage());
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), new Date());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



}
