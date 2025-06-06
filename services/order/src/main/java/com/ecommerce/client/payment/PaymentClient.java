package com.ecommerce.client.payment;

import com.ecommerce.client.payment.dto.PaymentRequest;
import com.ecommerce.client.payment.dto.PaymentResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="payment-service",url ="${application.config.payment-url}" )
public interface PaymentClient {

    @PostMapping
    ResponseEntity<PaymentResponse> requestOrderPayment(@RequestBody PaymentRequest paymentRequest);
}
