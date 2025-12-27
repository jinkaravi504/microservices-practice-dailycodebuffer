package com.dailycodebuffer.productService.exception;

import lombok.Data;

import java.io.Serial;

@Data
public class ProductNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -8872729691561717423L;

    private String errorCode;

    public ProductNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
