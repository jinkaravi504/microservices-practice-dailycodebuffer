package com.dailycodebuffer.OrderService.exception;

import com.dailycodebuffer.OrderService.external.decoder.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequestMapping(produces = "application/json")
public class ExceptionTranslator {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(CustomException exception) {
        return new ResponseEntity<>(ErrorResponse.builder()
                                                 .errorMessage(exception.getMessage())
                                                 .errorCode(exception.getErrorCode())
                                                 .build(), HttpStatus.valueOf(exception.getStatus()));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(OrderNotFoundException exception) {
        return new ResponseEntity<>(ErrorResponse.builder()
                                                 .errorMessage(exception.getMessage())
                                                 .errorCode(exception.getErrorCode())
                                                 .build(), HttpStatus.NOT_FOUND);
    }

}
