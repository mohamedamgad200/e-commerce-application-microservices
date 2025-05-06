package com.ecommerce.controller;

import com.ecommerce.dto.CustomerRequest;
import com.ecommerce.entity.Customer;
import com.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    public ResponseEntity<Customer>createCustomer(@RequestBody @Valid CustomerRequest request){
        return null;
    }
}
