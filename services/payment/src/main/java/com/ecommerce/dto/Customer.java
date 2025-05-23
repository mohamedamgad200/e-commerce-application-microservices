package com.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class Customer {
    private String id;
    @NotNull(message = "Firstname is required")
    private String firstName;
    @NotNull(message = "Lastname is required")
    private String lastName;
    @NotNull(message = "Email is required")
    @Email(message = "The customer email is not correctly formatted")
    private String email;
}
