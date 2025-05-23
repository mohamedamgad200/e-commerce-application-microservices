package com.ecommerce.kafka.order;

import com.ecommerce.enums.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderConfirmation {
    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private CustomerResponse customer;
    private List<Product> products;
}
