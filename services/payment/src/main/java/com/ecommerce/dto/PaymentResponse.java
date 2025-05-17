package com.ecommerce.dto;

import com.ecommerce.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private Integer id;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private Integer orderId;
}
