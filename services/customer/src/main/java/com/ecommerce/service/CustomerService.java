package com.ecommerce.service;

import com.ecommerce.dto.ResoponseMessage;
import org.apache.commons.lang.StringUtils;
import com.ecommerce.dto.CustomerRequest;
import com.ecommerce.dto.CustomerResponse;
import com.ecommerce.entity.Customer;
import com.ecommerce.exception.CustomerNotFoundException;
import com.ecommerce.mapper.CustomerMapper;
import com.ecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    public CustomerResponse createCustomer(CustomerRequest customerRequest)
    {
        Customer customer=customerMapper.toCustomer(customerRequest);
        Customer savedCustomer=customerRepository.save(customer);
        return customerMapper.toCustomerResponse(savedCustomer);
    }
    public CustomerResponse updateCustomer(CustomerRequest customerRequest)
    {
        Customer customer=customerRepository.findById(customerRequest.getId()).orElseThrow(
                ()->new CustomerNotFoundException(
                        format("Cannot update customer:: No customer found with the provided ID: %s", customerRequest.getId()))
        );
        mergeCustomer(customer, customerRequest);
        Customer updatedCustomer=customerRepository.save(customer);
        return customerMapper.toCustomerResponse(updatedCustomer);
    }
    private void mergeCustomer(Customer customer,CustomerRequest customerRequest)
    {
        if(StringUtils.isNotBlank(customerRequest.getFirstName()))
        {
            customer.setFirstName(customerRequest.getFirstName());
        }
        if(StringUtils.isNotBlank(customerRequest.getLastName()))
        {
            customer.setLastName(customerRequest.getLastName());
        }
        if(StringUtils.isNotBlank(customerRequest.getEmail()))
        {
            customer.setEmail(customerRequest.getEmail());
        }
        if(customerRequest.getAddress()!=null)
        {
            customer.setAddress(customerRequest.getAddress());
        }
    }

    public List<CustomerResponse>findAll()
    {
        return customerRepository.findAll().stream()
                .map(customerMapper::toCustomerResponse)
                .toList();
    }

    public Boolean existsCustomerById(String customerId)
    {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findCustomerById(String customerId){
       return customerRepository.findById(customerId)
                .map(customerMapper::toCustomerResponse)
                .orElseThrow(()->
                new CustomerNotFoundException(
                        format("No customer found with the provided ID: %s", customerId))
                );
    }

    public ResoponseMessage deleteCustomer(String customerId)
    {
        customerRepository.deleteById(customerId);
        return ResoponseMessage.builder()
                .message("Customer deleted successfully")
                .dateTime(LocalDateTime.now())
                .build();
    }
}
