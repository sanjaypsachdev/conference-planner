package com.sanjaypsachdev.conferenceplanner.data;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class for a conference session.
 * Represents a session in the database with all required fields.
 * 
 * @author Sanjay P. Sachdev
 * @version 1.0
 * @since 2026-03-10
 */
@Entity
@Table(name = "sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String speaker;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String room;
    private String track;
}
