package com.ecommerce.mapper;

import com.ecommerce.dto.PaymentRequest;
import com.ecommerce.dto.PaymentResponse;
import com.ecommerce.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest paymentRequest)
    {
        return Payment.builder()
                .orderId(paymentRequest.getOrderId())
                .amount(paymentRequest.getAmount())
                .paymentMethod(paymentRequest.getPaymentMethod())
                .build();
    }
    public PaymentResponse fromPayment(Payment payment)
    {
        return PaymentResponse
                .builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .build();
    }
}
