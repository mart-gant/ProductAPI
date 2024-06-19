package com.example.productapi.dto;

import java.math.BigDecimal;

public record ProductRequest(String name, BigDecimal price) {
}
