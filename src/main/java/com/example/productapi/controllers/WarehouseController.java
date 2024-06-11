package com.example.productapi.controllers;

import com.example.productapi.models.Warehouse;
import com.example.productapi.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public List<Warehouse> getAllWarehouses() {
        return warehouseService.getAllWarehouses();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse) {
        return warehouseService.saveWarehouse(warehouse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @RequestBody Warehouse warehouseDetails) {
        return warehouseService.getWarehouseById(id)
                .map(warehouse -> {
                    warehouse.setName(warehouseDetails.getName());
                    warehouse.setAddress(warehouseDetails.getAddress());
                    return ResponseEntity.ok(warehouseService.saveWarehouse(warehouse));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        if(warehouseService.getWarehouseById(id).isPresent()) {
            warehouseService.deleteWarehouse(id);
            return ResponseEntity.noContent().build();

        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
