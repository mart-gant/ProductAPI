package com.example.productapi.services;

import com.example.productapi.dto.AvailabilityRequest;
import com.example.productapi.models.Availability;
import com.example.productapi.repositories.AvailabilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AvailabilityServiceTest {

    @Mock
    private AvailabilityRepository availabilityRepository;

    @InjectMocks
    private AvailabilityService availabilityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAvailability() {
        AvailabilityRequest request = new AvailabilityRequest(1L, 1L, 10);
        Availability availability = new Availability();
        availability.setId(1L);
        availability.setProductId(request.productId());
        availability.setWarehouseId(request.warehouseId());
        availability.setQuantity(request.quantity());

        when(availabilityRepository.save(any(Availability.class))).thenReturn(availability);

        assertEquals(1L, availabilityService.createAvailability(request).id());
        verify(availabilityRepository, times(1)).save(any(Availability.class));
    }

    @Test
    public void testDeleteAvailability() {
        availabilityService.deleteAvailability(1L);
        verify(availabilityRepository, times(1)).deleteById(1L);
    }
}


