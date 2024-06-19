package com.example.productapi.services;

import com.example.productapi.dto.WarehouseRequest;
import com.example.productapi.models.Warehouse;
import com.example.productapi.repositories.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    public Warehouse createWarehouse(WarehouseRequest request) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(request.name());
        warehouse.setAddress(request.address());
        return warehouseRepository.save(warehouse);
    }

    public Optional<Warehouse> updateWarehouse(Long id, WarehouseRequest request) {
        return warehouseRepository.findById(id)
                .map(existingWarehouse -> {
                    existingWarehouse.setName(request.name());
                    existingWarehouse.setAddress(request.address());
                    return warehouseRepository.save(existingWarehouse);
                });
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }
}
