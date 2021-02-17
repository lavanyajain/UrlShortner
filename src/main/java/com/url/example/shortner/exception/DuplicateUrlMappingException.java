package com.url.example.shortner.exception;

public class DuplicateUrlMappingException extends RuntimeException {
    public DuplicateUrlMappingException(String message) {
        super(message);
    }
}
