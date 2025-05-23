package com.ecommerce.service;

import com.ecommerce.dto.ProductPurchaseRequest;
import com.ecommerce.dto.ProductPurchaseResponse;
import com.ecommerce.dto.ProductRequest;
import com.ecommerce.dto.ProductResponse;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.exception.ProductPurchaseException;
import com.ecommerce.mapper.ProductMapper;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        Product savedProduct = productRepository.save(product);
        Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
        ProductResponse productResponse = productMapper.toProductResponse(savedProduct);
        productResponse.setCategoryName(category.getName());
        productResponse.setCategoryDescription(category.getDescription());
        return productResponse;
    }

    public ProductResponse findProductById(Integer id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new ProductNotFoundException(format("Product not found with ID: " + id)));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
        List<Integer> productIds = requests.stream()
                .map(ProductPurchaseRequest::getProductId)
                .toList();
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }
        List<ProductPurchaseRequest> sortedRequests = requests.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .toList();
        List<ProductPurchaseResponse> productPurchaseResponses = new ArrayList<>();
        for (int i = 0; i < storedProducts.size(); i++) {
            Product product = storedProducts.get(i);
            ProductPurchaseRequest productRequest = sortedRequests.get(i);
            if (product.getAvailableQuantity() < productRequest.getQuantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.getProductId());
            }
            double newAvailableQuantity = product.getAvailableQuantity() - productRequest.getQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            productPurchaseResponses.add(productMapper.toPurchaseResponse(product,productRequest.getQuantity()));
        }
        return productPurchaseResponses;
    }


}
