package com.url.example.shortner.exception;

public class InvalidUriException extends RuntimeException {
    public InvalidUriException(String message) {
        super(message);
    }
}
