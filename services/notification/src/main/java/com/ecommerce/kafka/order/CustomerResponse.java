package com.ecommerce.kafka.order;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
