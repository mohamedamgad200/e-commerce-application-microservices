package com.ecommerce.client.product.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PurchaseResponse {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}
