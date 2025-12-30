package com.dailycodebuffer.PaymentService.dto;

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
public class TransactionDetailsDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2820310469705760844L;

    private Long id;
    private Long orderId;
    private PaymentMode paymentMode;
    private String referenceNumber;
    private Instant paymentDate;
    private String paymentStatus;
    private BigDecimal amount;
}
