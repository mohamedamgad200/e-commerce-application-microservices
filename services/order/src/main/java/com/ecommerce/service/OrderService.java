package com.ecommerce.service;

import com.ecommerce.client.customer.CustomerClient;
import com.ecommerce.client.customer.dto.CustomerResponse;
import com.ecommerce.client.payment.PaymentClient;
import com.ecommerce.client.payment.dto.PaymentRequest;
import com.ecommerce.client.product.ProductClient;
import com.ecommerce.client.product.dto.PurchaseRequest;
import com.ecommerce.client.product.dto.PurchaseResponse;
import com.ecommerce.dto.OrderLineRequest;
import com.ecommerce.dto.OrderRequest;
import com.ecommerce.dto.OrderResponse;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderLine;
import com.ecommerce.exception.BusinessException;
import com.ecommerce.kafka.OrderConfirmation;
import com.ecommerce.kafka.OrderProducer;
import com.ecommerce.mapper.OrderMapper;
import com.ecommerce.repository.OrderLineRepository;
import com.ecommerce.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderLineService orderLineService;
    private final OrderMapper orderMapper;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public OrderResponse createOrder(OrderRequest orderRequest) {
        //1- check the customer --> customer microservice -->OpenFeign
        CustomerResponse customerResponse=customerClient.findCustomerById(orderRequest.getCustomerId())
                .orElseThrow(()->new BusinessException(
                        String.format("Cannot create order:: No customer exists with the provided ID %s"
                                , orderRequest.getCustomerId())));
        //2- purchase the products --> product microservice --> RestTemplate
        List<PurchaseResponse> purchaseProducts=this.productClient.purchaseProducts(orderRequest.getProducts());
        //3- persist order
        Order order =this.orderRepository.save(orderMapper.toOrder(orderRequest));
        //persist order lines
        for(PurchaseRequest purchaseRequest:orderRequest.getProducts())
        {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                    order.getId(),
                    purchaseRequest.getProductId(),
                    purchaseRequest.getQuantity()
                    )
            );
        }
        //send order confirmation --> notification microservice (Kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.getReference(),
                        orderRequest.getAmount(),
                        orderRequest.getPaymentMethod(),
                        customerResponse,
                        purchaseProducts
                        )
        );
        //start payment process --> payment microservice
        PaymentRequest paymentRequest= new PaymentRequest(
                orderRequest.getAmount(),
                orderRequest.getPaymentMethod(),
                order.getId(),
                order.getReference(),
                customerResponse
        );
        this.paymentClient.requestOrderPayment(paymentRequest);
        return orderMapper.fromOrder(order);
    }

    public List<OrderResponse>findAll()
    {
        return orderRepository
                .findAll()
                .stream()
                .map(this.orderMapper::fromOrder)
                .toList();
    }
    public OrderResponse findOrderById(int orderId)
    {
        return orderRepository.findById(orderId).map(this.orderMapper::fromOrder)
                .orElseThrow(()->
                        new EntityNotFoundException(
                                String.format("No order found with the provided ID: %d", orderId
                                )
                        )
                );
    }
}
