package com.dailycodebuffer.OrderService.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class ProductDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2784182283430809860L;

    private Long id;
    private String productName;
    private BigDecimal price;
    private Long quantity;

}
