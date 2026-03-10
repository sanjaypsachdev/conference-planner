package com.sanjaypsachdev.conferenceplanner.api;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for health check endpoint.
 * Returns a simple health status response with timestamp and application name.
 * 
 * @author Sanjay P. Sachdev
 * @version 1.0
 * @since 2026-03-10
 */
@RestController
@RequestMapping("/api/health")
public class HealthController {
    @GetMapping
    public Map<String, Object> health() {
        return Map.of(
                "status", "UP",
                "timestamp", System.currentTimeMillis(),
                "app", "Conference Planner"
        );
    }
}
