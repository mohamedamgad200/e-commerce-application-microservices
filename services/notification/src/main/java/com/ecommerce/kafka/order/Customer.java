package com.ecommerce.kafka.order;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
}
