package com.ecommerce.mapper;

import com.ecommerce.dto.OrderRequest;
import com.ecommerce.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest orderRequest)
    {
        return Order.builder()
                .customerId(orderRequest.getCustomerId())
                .reference(orderRequest.getReference())
                .paymentMethod(orderRequest.getPaymentMethod())
                .totalAmount(orderRequest.getAmount())
                .build();
    }
}
