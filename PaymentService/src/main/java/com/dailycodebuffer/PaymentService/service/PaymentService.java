package com.dailycodebuffer.PaymentService.service;

import com.dailycodebuffer.PaymentService.dto.TransactionDetailsDTO;

public interface PaymentService {
    TransactionDetailsDTO doPayment(TransactionDetailsDTO dto);

    TransactionDetailsDTO transactionDetails(Long orderId);
}
