package com.dailycodebuffer.productService.exception;

import com.dailycodebuffer.productService.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequestMapping(produces = "application/json")
public class ExceptionTranslator {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException exception) {
        return new ResponseEntity<>(ErrorResponse.builder()
                                                 .errorMessage(exception.getMessage())
                                                 .errorCode(exception.getErrorCode())
                                                 .build(), HttpStatus.NOT_FOUND);
    }


}
