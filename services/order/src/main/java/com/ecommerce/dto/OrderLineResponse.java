package com.ecommerce.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderLineResponse {
    private Integer id;
    private double quantity;
}
