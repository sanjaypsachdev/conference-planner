package com.sanjaypsachdev.conferenceplanner.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sanjaypsachdev.conferenceplanner.data.SessionEntity;
import com.sanjaypsachdev.conferenceplanner.data.SessionRepository;

/**
 * Configuration for seeding initial data into the database.
 * Checks if there are any sessions in the database and seeds them if not.
 * 
 * @author Sanjay P. Sachdev
 * @version 1.0
 * @since 2026-03-10
 */
@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner initData(SessionRepository sessionRepository) {
        return args -> {
            if (sessionRepository.count() == 0) {
                System.out.println("Seeding initial data...");

                List<SessionEntity> sessions = List.of(
                    new SessionEntity(
                        null,
                        "Spring Boot Best Practices",
                        "John Smith",
                        LocalDateTime.of(2026, 3, 15, 9, 0),
                        LocalDateTime.of(2026, 3, 15, 10, 0),
                        "Hall A",
                        "Backend"
                    ),
                    new SessionEntity(
                        null,
                        "React Performance Optimization",
                        "Sarah Johnson",
                        LocalDateTime.of(2026, 3, 15, 10, 30),
                        LocalDateTime.of(2026, 3, 15, 11, 30),
                        "Hall B",
                        "Frontend"
                    ),
                    new SessionEntity(
                        null,
                        "Microservices Architecture Patterns",
                        "Mike Chen",
                        LocalDateTime.of(2026, 3, 15, 13, 0),
                        LocalDateTime.of(2026, 3, 15, 14, 0),
                        "Hall A",
                        "Architecture"
                    ),
                    new SessionEntity(
                        null,
                        "Database Optimization Techniques",
                        "Emily Davis",
                        LocalDateTime.of(2026, 3, 15, 14, 30),
                        LocalDateTime.of(2026, 3, 15, 15, 30),
                        "Hall C",
                        "Database"
                    ),
                    new SessionEntity(
                        null,
                        "Cloud Deployment with Kubernetes",
                        "David Wilson",
                        LocalDateTime.of(2026, 3, 16, 9, 0),
                        LocalDateTime.of(2026, 3, 16, 10, 0),
                        "Hall B",
                        "DevOps"
                    )
                );

                sessionRepository.saveAll(sessions);
                System.out.println("Data seeding completed. Loaded " + sessions.size() + " sessions.");
            }
        };
    }
}
