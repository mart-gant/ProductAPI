package com.example.productapi.repositories;

import com.example.productapi.models.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    long countByProductIdAndQuantityGreaterThan(Long productId, int quantity);
}
