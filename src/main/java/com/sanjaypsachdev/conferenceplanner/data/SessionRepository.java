package com.sanjaypsachdev.conferenceplanner.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for session data access.
 * Provides methods for querying sessions by track and speaker.
 * 
 * @author Sanjay P. Sachdev
 * @version 1.0
 * @since 2026-03-10
 */
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
    List<SessionEntity> findByTrackIgnoreCase(String track);
    List<SessionEntity> findBySpeakerContainingIgnoreCase(String speaker);
}
