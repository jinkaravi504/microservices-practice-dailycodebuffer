package com.dailycodebuffer.OrderService.external.client;

import com.dailycodebuffer.OrderService.dto.TransactionDetailsDTO;
import com.dailycodebuffer.OrderService.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@CircuitBreaker(name = "external", fallbackMethod = "fallBack")
@FeignClient(name = "payment-service/api/payments")
public interface PaymentServiceClient {

    @PostMapping("/doPayment")
    public ResponseEntity<TransactionDetailsDTO> doPayment(TransactionDetailsDTO dto);

    default void fallBack(Exception exception) {
        throw new CustomException("Payment Service Not Availabel", "Unavialable", 500);
    }
}
