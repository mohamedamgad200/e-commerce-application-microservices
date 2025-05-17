package com.ecommerce.client.payment.dto;

import com.ecommerce.client.customer.dto.CustomerResponse;
import com.ecommerce.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PaymentRequest {
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private Integer orderId;
    private String orderReference;
    private CustomerResponse customer;
}
