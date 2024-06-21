package com.example.productapi.services;

import com.example.productapi.dto.ProductRequest;
import com.example.productapi.dto.ProductResponse;
import com.example.productapi.models.Product;
import com.example.productapi.exception.CustomException;
import com.example.productapi.repositories.AvailabilityRepository;
import com.example.productapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final AvailabilityRepository availabilityRepository;

    public ProductService(ProductRepository productRepository, AvailabilityRepository availabilityRepository) {
        this.productRepository = productRepository;
        this.availabilityRepository = availabilityRepository;
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponse> getProductById(Long id) {
        return productRepository.findById(id).map(this::mapToProductResponse);
    }

    public ProductResponse createProduct(ProductRequest request) {
        Product product = Product.createProduct();
        assert product != null;
        product.setName(request.name());
        product.setPrice(request.price());
        return mapToProductResponse(productRepository.save(product));
    }

    public Optional<ProductResponse> updateProduct(Long id, ProductRequest request) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(request.name());
                    existingProduct.setPrice(request.price());
                    return mapToProductResponse(productRepository.save(existingProduct));
                });
    }

    public void deleteProduct(Long id) {
        long availabilityCount = availabilityRepository.countByProductIdAndQuantityGreaterThan(id, 0);
        if (availabilityCount > 0) {
            throw new CustomException("Cannot delete product with existing inventory.");
        }
        productRepository.deleteById(id);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }
}
