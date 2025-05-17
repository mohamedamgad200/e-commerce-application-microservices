package com.ecommerce.service;

import com.ecommerce.dto.OrderLineRequest;
import com.ecommerce.dto.OrderLineResponse;
import com.ecommerce.entity.OrderLine;
import com.ecommerce.mapper.OrderLineMappper;
import com.ecommerce.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMappper orderLineMappper;
    public Integer saveOrderLine(OrderLineRequest orderLineRequest)
    {
        OrderLine orderLine = orderLineMappper.toOrderLine(orderLineRequest);
        return  orderLineRepository.save(orderLine).getId();
    }
    public List<OrderLineResponse>findAllByOrderId(int orderId)
    {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(this.orderLineMappper::fromOrderLine)
                .toList();
    }
}
