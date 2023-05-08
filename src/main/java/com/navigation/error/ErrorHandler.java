package com.navigation.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(BaseStationNotFoundException.class)
    public ResponseEntity<Object> handleBaseStationNotFoundException(BaseStationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(MobileStationNotFoundException.class)
    public ResponseEntity<Object> handleMobileStationNotFoundException(MobileStationNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }
}
