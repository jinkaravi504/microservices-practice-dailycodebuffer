package com.dailycodebuffer.OrderService.exception;

import lombok.Data;

import java.io.Serial;

@Data
public class CustomException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2260992873829375037L;
    private String errorCode;
    private int status;

    public CustomException(String message, String errorCode, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
