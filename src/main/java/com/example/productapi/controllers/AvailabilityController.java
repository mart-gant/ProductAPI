package com.example.productapi.controllers;

import com.example.productapi.models.Availability;
import com.example.productapi.models.Product;
import com.example.productapi.services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availabilities")
public class AvailabilityController {
    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping
    public List<Availability> getAllAvailabilities() {
        return availabilityService.getAllAvailabilities();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Availability> getAvailabilityById(@PathVariable Long id) {
        return availabilityService.getAvailabilityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Availability createAvailability(@RequestParam Long availabilityId, @RequestParam Long warehouseId, @RequestParam int quantity) {
        return availabilityService.saveAvailability(availabilityId, warehouseId, quantity);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Availability> updateAvailability(@PathVariable Long id, @RequestParam int quantity) {
        return availabilityService.getAvailabilityById(id)
                .map(inventory -> {
                    inventory.setQuantity(quantity);
                    Availability availability = new Availability();
                    return ResponseEntity.ok(availabilityService.saveAvailability(inventory.getProduct().getId(), availability.getWarehouse().getId(), quantity));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(@PathVariable Long id) {
        if(availabilityService.getAvailabilityById(id).isPresent()) {
            availabilityService.deleteAvailability(id);
            return ResponseEntity.noContent().build();

        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}
