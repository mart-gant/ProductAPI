package com.example.productapi.services;

import com.example.productapi.models.Availability;
import com.example.productapi.models.Product;
import com.example.productapi.models.Warehouse;
import com.example.productapi.repositories.AvailabilityRepository;
import com.example.productapi.repositories.ProductRepository;
import com.example.productapi.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityService {
    @Autowired
    private AvailabilityRepository availabilityRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<Availability> getAllAvailabilities() {
        return availabilityRepository.findAll();
    }

    public Optional<Availability> getAvailabilityById(Long id) {
        return availabilityRepository.findById(id);
    }

    public Availability saveAvailability(Long productId, Long warehouseId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow();
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow();
        Availability availability = availabilityRepository.findByProductAndWarehouse(product, warehouse);

        if(availability == null) {
            availability = new Availability();
            availability.setProduct(product);
            availability.setWarehouse(warehouse);

        }
        availability.setQuantity(quantity);
        return availabilityRepository.save(availability);
    }

    public void deleteAvailability(Long id) {
        availabilityRepository.deleteById(id);
    }

    public Availability saveAvailability(Availability availability) {
    }
}
