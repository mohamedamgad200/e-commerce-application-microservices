package com.ecommerce.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderResponse {
    private Integer id;
    private double quantity;
}
