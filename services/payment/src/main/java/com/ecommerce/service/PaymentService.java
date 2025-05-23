package com.ecommerce.service;

import com.ecommerce.dto.PaymentRequest;
import com.ecommerce.dto.PaymentResponse;
import com.ecommerce.entity.Payment;
import com.ecommerce.kafka.NotificationProducer;
import com.ecommerce.kafka.PaymentNotificationRequest;
import com.ecommerce.mapper.PaymentMapper;
import com.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        Payment payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        paymentRequest.getOrderReference(),
                        paymentRequest.getAmount(),
                        paymentRequest.getPaymentMethod(),
                        paymentRequest.getCustomer().getFirstName(),
                        paymentRequest.getCustomer().getLastName(),
                        paymentRequest.getCustomer().getEmail()
                )
        );
        return paymentMapper.fromPayment(payment);
    }
}
