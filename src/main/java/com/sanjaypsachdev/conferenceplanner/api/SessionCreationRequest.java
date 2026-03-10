package com.sanjaypsachdev.conferenceplanner.api;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Request object for creating a new conference session.
 * Contains all required fields for session creation.
 * 
 * @author Sanjay P. Sachdev
 * @version 1.0
 * @since 2026-03-10
 */
public record SessionCreationRequest(
     @NotNull @Size(min = 3, max = 120) String title,
     @NotNull @Size(min = 2, max = 80) String speaker,
     @NotNull LocalDateTime startTime,
     @NotNull LocalDateTime endTime,
     @NotBlank String room,
     @Size(max = 40) String track
) {}
