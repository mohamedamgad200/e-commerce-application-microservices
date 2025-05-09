package com.ecommerce.service;

import com.ecommerce.client.customer.CustomerClient;
import com.ecommerce.client.customer.dto.CustomerResponse;
import com.ecommerce.client.product.ProductClient;
import com.ecommerce.client.product.dto.PurchaseRequest;
import com.ecommerce.dto.OrderLineRequest;
import com.ecommerce.dto.OrderRequest;
import com.ecommerce.dto.OrderResponse;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderLine;
import com.ecommerce.exception.BusinessException;
import com.ecommerce.mapper.OrderMapper;
import com.ecommerce.repository.OrderLineRepository;
import com.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    public OrderResponse createOrder(OrderRequest orderRequest) {
        //1- check the customer --> customer microservice -->OpenFeign
        CustomerResponse customerResponse=customerClient.findCustomerById(orderRequest.getCustomerId())
                .orElseThrow(()->new BusinessException(
                        String.format("Cannot create order:: No customer exists with the provided ID %s"
                                , orderRequest.getCustomerId())));
        //2- purchase the products --> product microservice --> RestTemplate
        this.productClient.purchaseProducts(orderRequest.getProducts());
        //3- persist order
        this.orderRepository.save(orderMapper.toOrder(orderRequest));
        //persist order lines
        for(PurchaseRequest purchaseRequest:orderRequest.getProducts())
        {
        }
        //start payment process --> payment microservice
        //send order confirmation --> notification microservice (Kafka)

        return null;
    }
}
