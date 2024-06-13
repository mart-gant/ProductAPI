package com.example.productapi;

import com.example.productapi.controllers.ProductController;
import com.example.productapi.models.Product;
import com.example.productapi.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.nio.ch.DefaultSelectorProvider.get;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    @Test
    public void testgetProductById() throws Exception {
        when(productService.getProductById(1L)).thenReturn(Optional.of(new Product()));

        mockMvc.perform((RequestBuilder) get())
                .andExpect(status().isOk());

    }

}
