package com.ecommerce.dto;

import com.ecommerce.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CustomerRequest {

    private String id;

    @NotNull(message = "Customer firstname is required")
    private String firstName;

    @NotNull(message = "Customer firstname is required")
    private String lastName;

    @NotNull(message="Customer Email is required")
    @Email(message="Customer Email is not a valid email address")
    private String email;

    private Address address;
}
