package com.example.productapi.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
