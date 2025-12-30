package com.dailycodebuffer.PaymentService.controller;

import com.dailycodebuffer.PaymentService.dto.TransactionDetailsDTO;
import com.dailycodebuffer.PaymentService.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {

    private final PaymentService paymentService;

    @PostMapping("/doPayment")
    public ResponseEntity<TransactionDetailsDTO> doPayment(@RequestBody TransactionDetailsDTO dto) {
        dto = paymentService.doPayment(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<TransactionDetailsDTO> transactionDetails(@PathVariable("orderId") Long orderId) {
        TransactionDetailsDTO transactionDetails = paymentService.transactionDetails(orderId);
        return new ResponseEntity<>(transactionDetails, HttpStatus.OK);
    }

}
