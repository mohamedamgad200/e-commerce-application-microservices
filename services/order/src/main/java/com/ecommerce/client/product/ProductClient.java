package com.ecommerce.client.product;

import com.ecommerce.client.product.dto.PurchaseRequest;
import com.ecommerce.client.product.dto.PurchaseResponse;
import com.ecommerce.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
public class ProductClient {
    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;

    public List<PurchaseResponse>purchaseProducts(List<PurchaseRequest> requests)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, APPLICATION_JSON_VALUE);
        HttpEntity<List<PurchaseRequest>> requestsEntity = new HttpEntity<>(requests, headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<List<PurchaseResponse>>() {};
        ResponseEntity<List<PurchaseResponse>>responseEntity=restTemplate.exchange(
                productUrl+"/purchase",
                HttpMethod.POST,
                requestsEntity,
                responseType);
        if(responseEntity.getStatusCode().isError())
        {
            throw  new BusinessException("An error occurred while processing the products purchase: " + responseEntity.getStatusCode());
        }
        return  responseEntity.getBody();
    }
}
