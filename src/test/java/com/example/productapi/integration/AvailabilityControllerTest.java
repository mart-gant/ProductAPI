package com.example.productapi.integration;

import com.example.productapi.models.Product;
import com.example.productapi.models.Warehouse;
import com.example.productapi.repositories.AvailabilityRepository;
import com.example.productapi.repositories.ProductRepository;
import com.example.productapi.repositories.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AvailabilityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    private Product product;
    private Warehouse warehouse;

    @BeforeEach
    public void setUp() {
        availabilityRepository.deleteAll();
        productRepository.deleteAll();
        warehouseRepository.deleteAll();

        product = new Product();
        product.setName("Product 1");
        product.setPrice(new BigDecimal("9.99"));
        productRepository.save(product);

        warehouse = new Warehouse();
        warehouse.setName("Warehouse 1");
        warehouse.setAddress("123 Warehouse Street");
        warehouseRepository.save(warehouse);
    }

    @Test
    public void testCreateAvailability() throws Exception {
        mockMvc.perform(post("/availabilities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "productId": %d,
                                    "warehouseId": %d,
                                    "quantity": 10
                                }
                                """.formatted(product.getId(), warehouse.getId())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productId").value(product.getId()))
                .andExpect(jsonPath("$.warehouseId").value(warehouse.getId()))
                .andExpect(jsonPath("$.quantity").value(10));
    }
}
