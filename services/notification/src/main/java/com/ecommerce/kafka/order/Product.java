package com.ecommerce.kafka.order;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}
