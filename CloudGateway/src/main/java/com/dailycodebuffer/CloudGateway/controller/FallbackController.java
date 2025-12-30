package com.dailycodebuffer.CloudGateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @GetMapping("/order-fallback")
    public Mono<String> orderFallback() {
        return Mono.just("Order Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/payment-fallback")
    public Mono<String> paymentFallback() {
        return Mono.just("payment Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/product-fallback")
    public Mono<String> productFallback() {
        return Mono.just("product Service is currently unavailable. Please try again later.");
    }
}
