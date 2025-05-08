package com.ecommerce.mapper;

import com.ecommerce.dto.ProductPurchaseRequest;
import com.ecommerce.dto.ProductPurchaseResponse;
import com.ecommerce.dto.ProductRequest;
import com.ecommerce.dto.ProductResponse;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest productRequest)
    {
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .availableQuantity(productRequest.getAvailableQuantity())
                .category(
                        Category.builder()
                        .id(productRequest.getCategoryId())
                        .build())
                .build();
    }

    public ProductResponse toProductResponse(Product product)
    {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .categoryId(product.getCategory().getId())
                .categoryDescription(product.getCategory().getDescription())
                .categoryName(product.getCategory().getName())
                .build();
    }

    public ProductPurchaseResponse toPurchaseResponse(Product product ,double quantity)
    {
        return ProductPurchaseResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(quantity)
                .build();
    }
}
