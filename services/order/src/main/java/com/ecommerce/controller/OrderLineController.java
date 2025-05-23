package com.ecommerce.controller;

import com.ecommerce.dto.OrderLineResponse;
import com.ecommerce.entity.OrderLine;
import com.ecommerce.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {
    private final OrderLineService orderLineService;
    @GetMapping("/orders/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable("order-id") Integer orderId)
    {
        return new ResponseEntity<>(orderLineService.findAllByOrderId(orderId), HttpStatus.OK);
    }
}
