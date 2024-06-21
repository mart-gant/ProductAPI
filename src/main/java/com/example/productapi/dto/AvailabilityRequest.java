package com.example.productapi.dto;

public record AvailabilityRequest(Long productId, Long warehouseId, int quantity) {
}