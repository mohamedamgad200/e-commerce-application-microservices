package com.ecommerce.mapper;

import com.ecommerce.dto.OrderLineRequest;
import com.ecommerce.dto.OrderLineResponse;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMappper {

    public OrderLine toOrderLine(OrderLineRequest orderLineRequest)
    {
        return OrderLine.builder()
                .order(
                        Order.builder()
                                .id(orderLineRequest.getOrderId())
                        .build())
                .productId(orderLineRequest.getProductId())
                .quantity(orderLineRequest.getQuantity())
                .build();
    }
    public OrderLineResponse fromOrderLine(OrderLine orderLine)
    {
        return  OrderLineResponse
                .builder()
                .id(orderLine.getId())
                .quantity(orderLine.getQuantity())
                .build();
    }
}
