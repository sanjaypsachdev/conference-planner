package com.sanjaypsachdev.conferenceplanner.error;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }

}
