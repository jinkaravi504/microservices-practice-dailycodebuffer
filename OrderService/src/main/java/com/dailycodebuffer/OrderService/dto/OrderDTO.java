package com.dailycodebuffer.OrderService.dto;

import com.dailycodebuffer.OrderService.entity.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 9127403834065483493L;

    private Long id;
    private Long productId;
    private Long quantity;
    private Instant orderDate;
    private String orderStatus;
    private BigDecimal amount;
    private PaymentMode paymentMode;
    private ProductDTO productDetails;
    private TransactionDetailsDTO transactionDetails;

}
