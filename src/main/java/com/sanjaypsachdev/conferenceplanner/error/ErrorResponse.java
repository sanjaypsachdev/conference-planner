package com.sanjaypsachdev.conferenceplanner.error;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
    LocalDateTime timestamp,
    int status,
    String error,
    String path,
    String message,
    List<String> details
) {
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String path, String message) {
        this(timestamp, status, error, path, message, null);
    }
}
