package com.ecommerce.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderLineRequest {
    private Integer orderId;
    private Integer productId;
    private double quantity;
}
