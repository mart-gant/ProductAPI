package com.example.productapi.repositories;

import com.example.productapi.models.Availability;
import com.example.productapi.models.Product;
import com.example.productapi.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    <Inventory> Inventory findByProductAndWarehouse(Product product, Warehouse warehouse);
}
