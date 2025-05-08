package com.ecommerce.controller;

import com.ecommerce.dto.ProductPurchaseRequest;
import com.ecommerce.dto.ProductPurchaseResponse;
import com.ecommerce.dto.ProductRequest;
import com.ecommerce.dto.ProductResponse;
import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
     return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>>purchaseProducts(@Valid @RequestBody List<ProductPurchaseRequest> requests)
    {
        return new ResponseEntity<>(productService.purchaseProducts(requests),HttpStatus.CREATED);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse>findProductById(@PathVariable("product-id") int productId)
    {
        return new ResponseEntity<>(productService.findProductById(productId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>>findAll()
    {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
}
