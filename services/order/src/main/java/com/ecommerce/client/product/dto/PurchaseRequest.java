package com.ecommerce.client.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PurchaseRequest {
    @NotNull(message = "Product is mandatory")
    Integer productId;
    @Positive(message = "Quantity is mandatory")
    double quantity;
}
