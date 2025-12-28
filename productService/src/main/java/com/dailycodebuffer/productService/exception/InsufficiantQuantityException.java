package com.dailycodebuffer.productService.exception;

import lombok.Data;

import java.io.Serial;

@Data
public class InsufficiantQuantityException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4387237084523571575L;

    private String errorCode;

    public InsufficiantQuantityException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
