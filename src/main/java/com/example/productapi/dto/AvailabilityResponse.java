package com.example.productapi.dto;

public record AvailabilityResponse(Long id, Long productId, Long warehouseId, int quantity) {
}
