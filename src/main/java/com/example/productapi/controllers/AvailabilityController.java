package com.example.productapi.controllers;

import com.example.productapi.dto.AvailabilityRequest;
import com.example.productapi.dto.AvailabilityResponse;
import com.example.productapi.services.AvailabilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/availabilities")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @GetMapping
    public List<AvailabilityResponse> getAllAvailabilities() {
        return availabilityService.getAllAvailabilities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailabilityResponse> getAvailabilityById(@PathVariable Long id) {
        Optional<AvailabilityResponse> availabilityResponse = availabilityService.getAvailabilityById(id);
        return availabilityResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AvailabilityResponse createAvailability(@RequestBody AvailabilityRequest request) {
        return availabilityService.createAvailability(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvailabilityResponse> updateAvailability(@PathVariable Long id, @RequestBody AvailabilityRequest request) {
        Optional<AvailabilityResponse> availabilityResponse = availabilityService.updateAvailability(id, request);
        return availabilityResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long id) {
        availabilityService.deleteAvailability(id);
        return ResponseEntity.noContent().build();
    }
}
