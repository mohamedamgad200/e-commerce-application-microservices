package com.ecommerce.kafka;

import com.ecommerce.entity.Notification;
import com.ecommerce.kafka.order.OrderConfirmation;
import com.ecommerce.kafka.payment.PaymentNotificationRequest;
import com.ecommerce.repository.NotificationRepository;
import com.ecommerce.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.ecommerce.enums.NotificationType.ORDER_CONFIRMATION;
import static com.ecommerce.enums.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationsConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic",groupId = "paymentGroup")
    public void consumePaymentSuccessNotification(PaymentNotificationRequest paymentConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s",paymentConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );
        String customerName= paymentConfirmation.getCustomerFirstname()+" "+paymentConfirmation.getCustomerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.getCustomerEmail(),
                customerName,
                paymentConfirmation.getAmount(),
                paymentConfirmation.getOrderReference()
        );

    }

    @KafkaListener(topics = "order-topic",groupId ="orderGroup" )
    public void consumeOrderConfirmationNotifications(OrderConfirmation orderConfirmation) throws MessagingException  {
        log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        String customerName= orderConfirmation.getCustomer().getFirstName()+" "+orderConfirmation.getCustomer().getLastName();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.getCustomer().getEmail(),
                customerName,
                orderConfirmation.getTotalAmount(),
                orderConfirmation.getOrderReference(),
                orderConfirmation.getProducts()
        );
    }
}
