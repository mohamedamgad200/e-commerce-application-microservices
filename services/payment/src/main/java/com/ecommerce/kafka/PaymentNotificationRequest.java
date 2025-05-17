package com.ecommerce.kafka;

import com.ecommerce.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentNotificationRequest {
    private String orderReference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String customerFirstname;
    private String customerLastname;
    private String customerEmail;
}
