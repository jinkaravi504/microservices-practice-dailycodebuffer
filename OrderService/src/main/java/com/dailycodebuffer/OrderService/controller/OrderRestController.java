package com.dailycodebuffer.OrderService.controller;

import com.dailycodebuffer.OrderService.dto.OrderDTO;
import com.dailycodebuffer.OrderService.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{orderId}")
    public ResponseEntity<OrderDTO> getOrderDeatils(@PathVariable("orderId") Long orderId) {
        OrderDTO orderDTO = orderService.getOrderDetails(orderId);
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);

    }
}
