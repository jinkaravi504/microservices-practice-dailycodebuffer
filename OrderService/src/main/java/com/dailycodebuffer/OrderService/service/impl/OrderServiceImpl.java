package com.dailycodebuffer.OrderService.service.impl;

import com.dailycodebuffer.OrderService.dto.OrderDTO;
import com.dailycodebuffer.OrderService.entity.Order;
import com.dailycodebuffer.OrderService.repository.OrderRepository;
import com.dailycodebuffer.OrderService.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Log4j2
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Long placeOrder(OrderDTO orderDTO) {
        log.info("OrderServiceImpl : placeOrder : START");
        Order order = Order.builder()
                           .productId(orderDTO.getProductId())
                           .amount(orderDTO.getAmount())
                           .orderStatus("CREATED")
                           .orderDate(Instant.now())
                           .quantity(orderDTO.getQuantity())
                           .build();
        order = orderRepository.save(order);
        log.info("OrderServiceImpl : placeOrder : END");
        return order.getId();
    }
}
