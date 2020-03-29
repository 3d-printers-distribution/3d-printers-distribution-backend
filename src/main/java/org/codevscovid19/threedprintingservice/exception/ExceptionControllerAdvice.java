package org.codevscovid19.threedprintingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RestError> badRequest(Exception e) {
        return ResponseEntity.badRequest().body(new RestError(HttpStatus.BAD_REQUEST, e.getClass().getSimpleName()));
    }
}
