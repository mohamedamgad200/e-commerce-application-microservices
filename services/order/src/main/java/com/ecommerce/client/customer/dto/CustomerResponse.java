package com.ecommerce.client.customer.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
