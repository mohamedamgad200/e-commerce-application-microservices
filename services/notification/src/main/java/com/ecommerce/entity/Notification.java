package com.ecommerce.entity;

import com.ecommerce.enums.NotificationType;
import com.ecommerce.kafka.order.OrderConfirmation;
import com.ecommerce.kafka.payment.PaymentNotificationRequest;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Notification {
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentNotificationRequest paymentConfirmation;
}
