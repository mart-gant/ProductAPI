package com.example.productapi.services;

import com.example.productapi.dto.AvailabilityRequest;
import com.example.productapi.dto.AvailabilityResponse;
import com.example.productapi.models.Availability;
import com.example.productapi.repositories.AvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    public AvailabilityService(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    public List<AvailabilityResponse> getAllAvailabilities() {
        return availabilityRepository.findAll().stream()
                .map(this::mapToAvailabilityResponse)
                .collect(Collectors.toList());
    }

    public Optional<AvailabilityResponse> getAvailabilityById(Long id) {
        return availabilityRepository.findById(id).map(this::mapToAvailabilityResponse);
    }

    public AvailabilityResponse createAvailability(AvailabilityRequest request) {
        Availability availability = new Availability();
        availability.setProductId(request.productId());
        availability.setWarehouseId(request.warehouseId());
        availability.setQuantity(request.quantity());
        return mapToAvailabilityResponse(availabilityRepository.save(availability));
    }

    public Optional<AvailabilityResponse> updateAvailability(Long id, AvailabilityRequest request) {
        return availabilityRepository.findById(id)
                .map(existingAvailability -> {
                    existingAvailability.setProductId(request.productId());
                    existingAvailability.setWarehouseId(request.warehouseId());
                    existingAvailability.setQuantity(request.quantity());
                    return mapToAvailabilityResponse(availabilityRepository.save(existingAvailability));
                });
    }

    public void deleteAvailability(Long id) {
        availabilityRepository.deleteById(id);
    }

    private AvailabilityResponse mapToAvailabilityResponse(Availability availability) {
        return new AvailabilityResponse(availability.getId(), availability.getProductId(), availability.getWarehouseId(), availability.getQuantity());
    }
}
