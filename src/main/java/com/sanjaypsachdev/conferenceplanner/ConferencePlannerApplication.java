package com.sanjaypsachdev.conferenceplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Conference Planner.
 * Bootstraps the Spring Boot application and starts the server.
 * 
 * @author Sanjay P. Sachdev
 * @version 1.0
 * @since 2026-03-10
 */
@SpringBootApplication
public class ConferencePlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConferencePlannerApplication.class, args);
    }
}
