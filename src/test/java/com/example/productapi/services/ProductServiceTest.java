package com.example.productapi.services;


import com.example.productapi.models.Product;
import com.example.productapi.exception.CustomException;
import com.example.productapi.repositories.AvailabilityRepository;
import com.example.productapi.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private AvailabilityRepository inventoryRepository;

    public ProductServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteProductWithInventoryThrowsException() {
        Long productId = 1L;
        when(inventoryRepository.countByProductIdAndQuantityGreaterThan(productId, 0)).thenReturn(1L);

        assertThrows(CustomException.class, () -> productService.deleteProduct(productId));
    }

    @Test
    public void testDeleteProductWithoutInventory() {
        Long productId = 1L;
        when(inventoryRepository.countByProductIdAndQuantityGreaterThan(productId, 0)).thenReturn(0L);
        assert Product.createProduct() != null;
        when(productRepository.findById(productId)).thenReturn(Optional.ofNullable(Product.createProduct()));

        productService.deleteProduct(productId);
    }
}

