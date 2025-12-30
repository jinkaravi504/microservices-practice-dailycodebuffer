package com.dailycodebuffer.OrderService.service;

import com.dailycodebuffer.OrderService.dto.OrderDTO;

public interface OrderService {

    public Long placeOrder(OrderDTO order);

    OrderDTO getOrderDetails(Long orderId);
}
