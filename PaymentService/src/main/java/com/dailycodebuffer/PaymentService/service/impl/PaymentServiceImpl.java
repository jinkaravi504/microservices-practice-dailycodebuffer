package com.dailycodebuffer.PaymentService.service.impl;

import com.dailycodebuffer.PaymentService.dto.TransactionDetailsDTO;
import com.dailycodebuffer.PaymentService.entity.TransactionDetails;
import com.dailycodebuffer.PaymentService.repository.TransactionDetailsRepository;
import com.dailycodebuffer.PaymentService.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Log4j2
@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public TransactionDetailsDTO doPayment(TransactionDetailsDTO dto) {
        TransactionDetails transactionDetails = TransactionDetails.builder()
                                                                  .paymentMode(dto.getPaymentMode().name())
                                                                  .paymentDate(Instant.now())
                                                                  .amount(dto.getAmount())
                                                                  .paymentStatus("SUCCESS")
                                                                  .referenceNumber(LocalDateTime.now().toString())
                                                                  .orderId(dto.getOrderId())
                                                                  .build();
        transactionDetails = transactionDetailsRepository.save(transactionDetails);
        dto.setId(transactionDetails.getId());
        dto.setPaymentStatus(transactionDetails.getPaymentStatus());
        log.info("PaymentServiceImpl : doPayment : SUCCESS : id : {}", transactionDetails.getId());
        return dto;
    }

    @Override
    public TransactionDetailsDTO transactionDetails(Long orderId) {
        TransactionDetails transactionDetails = transactionDetailsRepository.findByOrderId(orderId);
        return TransactionDetailsDTO.builder()
                                    .orderId(transactionDetails.getOrderId())
                                    .referenceNumber(transactionDetails.getReferenceNumber())
                                    .paymentDate(transactionDetails.getPaymentDate())
                                    .paymentStatus(transactionDetails.getPaymentStatus())
                                    .amount(transactionDetails.getAmount())
                                    .build();
    }
}
