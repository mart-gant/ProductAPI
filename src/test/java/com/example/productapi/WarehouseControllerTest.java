package com.example.productapi;

import com.example.productapi.controllers.WarehouseController;
import com.example.productapi.models.Warehouse;
import com.example.productapi.services.WarehouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.nio.ch.DefaultSelectorProvider.get;

@WebMvcTest(WarehouseController.class)
public class WarehouseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WarehouseService warehouseService;

    @Test
    public void testGetWarehouseById() throws Exception {
        when(warehouseService.getWarehouseById(1L)).thenReturn(Optional.of(new Warehouse(1L, "Test Warehouse", "Test Address")));

        mockMvc.perform((RequestBuilder) get())
                .andExpect(status().isOk());
    }
}
