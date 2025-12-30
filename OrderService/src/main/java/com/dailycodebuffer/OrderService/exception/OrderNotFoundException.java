package com.dailycodebuffer.OrderService.exception;

import lombok.Data;

import java.io.Serial;

@Data
public class OrderNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4113094983096684534L;
    private String errorCode;

    public OrderNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
