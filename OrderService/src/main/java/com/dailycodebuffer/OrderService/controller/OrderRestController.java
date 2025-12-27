package com.dailycodebuffer.OrderService.controller;

import com.dailycodebuffer.OrderService.dto.OrderDTO;
import com.dailycodebuffer.OrderService.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping("/place-order")
    private ResponseEntity<Long> placeOrder(@RequestBody OrderDTO order) {
        Long orderId = orderService.placeOrder(order);
        return new ResponseEntity<>(orderId, HttpStatusCode.valueOf(201));
    }
}
