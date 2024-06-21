package com.example.productapi.services;

import com.example.productapi.dto.WarehouseRequest;
import com.example.productapi.models.Warehouse;
import com.example.productapi.repositories.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WarehouseServiceTest {

    @Mock
    private WarehouseRepository warehouseRepository;

    @InjectMocks
    private WarehouseService warehouseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllWarehouses() {
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setId(1L);
        warehouse1.setName("Warehouse 1");
        warehouse1.setAddress("Address 1");

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setId(2L);
        warehouse2.setName("Warehouse 2");
        warehouse2.setAddress("Address 2");

        when(warehouseRepository.findAll()).thenReturn(List.of(warehouse1, warehouse2));

        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        assertEquals(2, warehouses.size());
        assertEquals("Warehouse 1", warehouses.get(0).getName());
        assertEquals("Warehouse 2", warehouses.get(1).getName());
    }

    @Test
    public void testGetWarehouseById() {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(1L);
        warehouse.setName("Warehouse 1");
        warehouse.setAddress("Address 1");

        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(warehouse));

        Optional<Warehouse> foundWarehouse = warehouseService.getWarehouseById(1L);
        assertTrue(foundWarehouse.isPresent());
        assertEquals("Warehouse 1", foundWarehouse.get().getName());
    }

    @Test
    public void testGetWarehouseById_NotFound() {
        when(warehouseRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Warehouse> foundWarehouse = warehouseService.getWarehouseById(1L);
        assertFalse(foundWarehouse.isPresent());
    }

    @Test
    public void testCreateWarehouse() {
        WarehouseRequest request = new WarehouseRequest("New Warehouse", "New Address");
        Warehouse warehouse = new Warehouse();
        warehouse.setId(1L);
        warehouse.setName(request.name());
        warehouse.setAddress(request.address());

        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(warehouse);

        Warehouse createdWarehouse = warehouseService.createWarehouse(request);
        assertEquals("New Warehouse", createdWarehouse.getName());
        assertEquals("New Address", createdWarehouse.getAddress());
        assertEquals(1L, createdWarehouse.getId());
    }

    @Test
    public void testUpdateWarehouse() {
        Warehouse existingWarehouse = new Warehouse();
        existingWarehouse.setId(1L);
        existingWarehouse.setName("Existing Warehouse");
        existingWarehouse.setAddress("Existing Address");

        WarehouseRequest request = new WarehouseRequest("Updated Warehouse", "Updated Address");

        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(existingWarehouse));
        when(warehouseRepository.save(any(Warehouse.class))).thenReturn(existingWarehouse);

        Optional<Warehouse> updatedWarehouse = warehouseService.updateWarehouse(1L, request);

        assertTrue(updatedWarehouse.isPresent());
        assertEquals("Updated Warehouse", updatedWarehouse.get().getName());
        assertEquals("Updated Address", updatedWarehouse.get().getAddress());
    }

    @Test
    public void testUpdateWarehouse_NotFound() {
        WarehouseRequest request = new WarehouseRequest("Updated Warehouse", "Updated Address");

        when(warehouseRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Warehouse> updatedWarehouse = warehouseService.updateWarehouse(1L, request);
        assertFalse(updatedWarehouse.isPresent());
    }

    @Test
    public void testDeleteWarehouse() {
        warehouseService.deleteWarehouse(1L);
        verify(warehouseRepository, times(1)).deleteById(1L);
    }
}
