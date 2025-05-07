package com.ecommerce.controller;

import com.ecommerce.dto.CustomerRequest;
import com.ecommerce.dto.CustomerResponse;
import com.ecommerce.dto.ResoponseMessage;
import com.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse>createCustomer(@RequestBody @Valid CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CustomerResponse>updateCustomer(@RequestBody @Valid CustomerRequest customerRequest){
        return new ResponseEntity<>(customerService.updateCustomer(customerRequest),HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>>findAllCustomer(){
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean>existsCustomerById(@PathVariable("customer-id") String customerId){
        return new ResponseEntity<>(customerService.existsCustomerById(customerId), HttpStatus.OK);
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse>findCustomerById(@PathVariable("customer-id") String customerId)
    {
        return new ResponseEntity<>(customerService.findCustomerById(customerId), HttpStatus.OK);
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<ResoponseMessage>deleteCustomer(String customerId){
        return new ResponseEntity<>(customerService.deleteCustomer(customerId),HttpStatus.ACCEPTED);
    }
}
