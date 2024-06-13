package com.example.productapi;

import com.example.productapi.controllers.AvailabilityController;
import com.example.productapi.models.Availability;
import com.example.productapi.services.AvailabilityService;
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

@WebMvcTest(AvailabilityController.class)
public class AvailabilityControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AvailabilityService availabilityService;

    @Test
    public void testGetInventoryById() throws Exception {
        when(availabilityService.getAvailabilityById(1L)).thenReturn(Optional.of(new Availability()));

        mockMvc.perform((RequestBuilder) get())
                .andExpect(status().isOk());
    }
}
