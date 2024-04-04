package com.poonam.exception;

public class NonNullableAssociationException extends RuntimeException {
    public NonNullableAssociationException(String message) {
        super(message);
    }
}
