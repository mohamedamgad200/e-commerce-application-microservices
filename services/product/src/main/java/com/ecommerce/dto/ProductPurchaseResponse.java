package com.ecommerce.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductPurchaseResponse {
    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}
