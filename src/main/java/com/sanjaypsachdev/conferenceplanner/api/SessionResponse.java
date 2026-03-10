package com.sanjaypsachdev.conferenceplanner.api;

import java.time.LocalDateTime;

/**
 * Response object for a conference session.
 * Contains all fields of a session, suitable for API responses.
 * 
 * @author Sanjay P. Sachdev
 * @version 1.0
 * @since 2026-03-10
 */
public record SessionResponse(
     Long id,
     String title,
     String speaker,
     LocalDateTime startTime,
     LocalDateTime endTime,
     String room,
     String track
) {}
