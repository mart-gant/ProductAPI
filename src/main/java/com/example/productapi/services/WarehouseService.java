package com.example.productapi.services;

import com.example.productapi.models.Warehouse;
import com.example.productapi.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Warehouse> getAllWarehouses() {

        return warehouseRepository.findAll();
    }
    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }
    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }
    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);

    }
}
