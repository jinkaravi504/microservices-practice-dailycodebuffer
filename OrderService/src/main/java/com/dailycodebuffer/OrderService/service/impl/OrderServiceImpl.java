package com.dailycodebuffer.OrderService.service.impl;

import com.dailycodebuffer.OrderService.dto.OrderDTO;
import com.dailycodebuffer.OrderService.dto.PaymentMode;
import com.dailycodebuffer.OrderService.dto.TransactionDetailsDTO;
import com.dailycodebuffer.OrderService.entity.Order;
import com.dailycodebuffer.OrderService.entity.ProductDTO;
import com.dailycodebuffer.OrderService.exception.OrderNotFoundException;
import com.dailycodebuffer.OrderService.external.client.PaymentServiceClient;
import com.dailycodebuffer.OrderService.external.client.ProductServiceClient;
import com.dailycodebuffer.OrderService.repository.OrderRepository;
import com.dailycodebuffer.OrderService.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Log4j2
@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;
    private final PaymentServiceClient paymentServiceClient;
    private final RestTemplate restTemplate;

    @Override
    public Long placeOrder(OrderDTO orderDTO) {
        log.info("OrderServiceImpl : placeOrder : START");
        productServiceClient.reduceQuanitity(ProductDTO.builder()
                                                       .id(orderDTO.getProductId())
                                                       .quantity(orderDTO.getQuantity())
                                                       .build());
        Order order = Order.builder()
                           .productId(orderDTO.getProductId())
                           .amount(orderDTO.getAmount())
                           .orderStatus("CREATED")
                           .orderDate(Instant.now())
                           .quantity(orderDTO.getQuantity())
                           .build();
        order = orderRepository.save(order);

        log.info("Call Payment Service");
        TransactionDetailsDTO dto = TransactionDetailsDTO.builder()
                                                         .orderId(order.getId())
                                                         .referenceNumber("")
                                                         .amount(orderDTO.getAmount())
                                                         .paymentMode(PaymentMode.CASH)
                                                         .build();
        String paymentStatus = null;
        try {
            paymentServiceClient.doPayment(dto);
            paymentStatus = "SUCCESS";
            log.info("Payment done Successfully");
        } catch (Exception e) {
            paymentStatus = "FAILED";
            log.info("Payment Failed");
        }
        order.setOrderStatus(paymentStatus);
        orderRepository.save(order);
        log.info("OrderServiceImpl : placeOrder : END");
        return order.getId();
    }

    @Override
    public OrderDTO getOrderDetails(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order is not existing with id", "NOT_FOUND"));
        log.info("Invocking Product Service : productId :{} ", order.getProductId());
        ProductDTO productDTO = restTemplate.postForObject("http://product-service/api/products/" + order.getProductId(), null, ProductDTO.class);
        log.info("Invocking Payment Service : productId :{} ", order.getProductId());
        TransactionDetailsDTO transactionDetails = restTemplate.getForObject("http://payment-service/api/payments/order/" + order.getId(), TransactionDetailsDTO.class);
        return OrderDTO.builder()
                       .id(order.getId())
                       .orderStatus(order.getOrderStatus())
                       .amount(order.getAmount())
                       .orderDate(order.getOrderDate())
                       .quantity(order.getQuantity())
                       .productDetails(productDTO)
                       .transactionDetails(transactionDetails)
                       .build();
    }
}
