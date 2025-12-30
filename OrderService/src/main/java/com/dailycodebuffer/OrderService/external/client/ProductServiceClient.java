package com.dailycodebuffer.OrderService.external.client;

import com.dailycodebuffer.OrderService.entity.ProductDTO;
import com.dailycodebuffer.OrderService.exception.CustomException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CircuitBreaker(name = "external", fallbackMethod = "fallBack")
@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @PutMapping("/api/products/reduce-quantity")
    public ResponseEntity<Void> reduceQuanitity(@RequestBody ProductDTO productDTO);

    default void fallBack(Exception exception) {
        throw new CustomException("Product Service Not Availabel", "Unavialable", 500);
    }
}
