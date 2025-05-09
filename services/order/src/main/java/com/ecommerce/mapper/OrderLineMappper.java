package com.ecommerce.mapper;

import com.ecommerce.dto.OrderLineRequest;
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
}
