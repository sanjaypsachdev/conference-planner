package com.sanjaypsachdev.conferenceplanner.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanjaypsachdev.conferenceplanner.api.SessionCreationRequest;
import com.sanjaypsachdev.conferenceplanner.api.SessionResponse;
import com.sanjaypsachdev.conferenceplanner.api.SessionUpdateRequest;
import com.sanjaypsachdev.conferenceplanner.data.SessionEntity;
import com.sanjaypsachdev.conferenceplanner.data.SessionRepository;

/**
 * Service for conference session business logic and persistence.
 * Handles listing (with optional track/speaker filters), lookup by ID,
 * creation, update, and deletion of sessions. Validates start/end times
 * and maps between request/response DTOs and entities.
 *
 * @author Sanjay P. Sachdev
 * @version 1.0
 * @since 2025-03-10
 */
@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    /**
     * Creates the service with the given session repository.
     *
     * @param sessionRepository the repository used for session persistence
     */
    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    /**
     * Returns all sessions, optionally filtered by track and/or speaker.
     * When both are present, sessions must match both filters.
     *
     * @param track   optional track name to filter by (case-insensitive)
     * @param speaker optional speaker name to filter by (case-insensitive, substring match)
     * @return list of sessions as {@link SessionResponse}s
     */
    public List<SessionResponse> getAll(Optional<String> track, Optional<String> speaker) {
        List<SessionEntity> sessions;
        if (track.isPresent() && speaker.isPresent()) {
            sessions = sessionRepository.findByTrackIgnoreCase(track.get()).stream()
                    .filter(s -> s.getSpeaker().equalsIgnoreCase(speaker.get()))
                    .toList();
        } else if (track.isPresent()) {
            sessions = sessionRepository.findByTrackIgnoreCase(track.get());
        } else if (speaker.isPresent()) {
            sessions = sessionRepository.findBySpeakerContainingIgnoreCase(speaker.get());
        } else {
            sessions = sessionRepository.findAll();
        }
        return sessions.stream().map(this::mapToResponse).toList();
    }

    /**
     * Returns a session by its ID.
     *
     * @param id the session ID
     * @return the session as a {@link SessionResponse}
     * @throws IllegalArgumentException if no session exists with the given ID
     */
    public SessionResponse findById(Long id) {
        return sessionRepository
                .findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));
    }

    /**
     * Creates and persists a new session. Validates that start time is before end time.
     *
     * @param request the creation payload (title, speaker, times, room, track)
     * @return the created session as a {@link SessionResponse}
     * @throws IllegalArgumentException if start time is after end time
     */
    public SessionResponse addSession(SessionCreationRequest request) {
        validateTimes(request.startTime(), request.endTime());
        SessionEntity entity = new SessionEntity();
        mapToEntity(request, entity);
        return mapToResponse(sessionRepository.save(entity));
    }

    /**
     * Updates an existing session by ID. Validates that start time is before end time.
     *
     * @param id      the session ID to update
     * @param request the fields to update (title, speaker, times, room, track)
     * @return the updated session as a {@link SessionResponse}
     * @throws IllegalArgumentException if the session is not found or times are invalid
     */
    public SessionResponse updateSession(Long id, SessionUpdateRequest request) {
        validateTimes(request.startTime(), request.endTime());
        SessionEntity entity = sessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));
        mapToEntity(request, entity);
        return mapToResponse(sessionRepository.save(entity));
    }

    /**
     * Deletes a session by ID. Does nothing if the session does not exist after
     * confirming it exists (throws otherwise).
     *
     * @param id the session ID to delete
     * @throws IllegalArgumentException if no session exists with the given ID
     */
    public void deleteSession(Long id) {
        sessionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Session not found"));
        sessionRepository.deleteById(id);
    }

    /** Maps update request fields onto an existing entity. */
    private void mapToEntity(SessionUpdateRequest request, SessionEntity entity) {
        entity.setTitle(request.title());
        entity.setSpeaker(request.speaker());
        entity.setStartTime(request.startTime());
        entity.setEndTime(request.endTime());
        entity.setRoom(request.room());
        entity.setTrack(request.track());
    }

    /** Maps creation request fields onto a new entity. */
    private void mapToEntity(SessionCreationRequest request, SessionEntity entity) {
        entity.setTitle(request.title());
        entity.setSpeaker(request.speaker());
        entity.setStartTime(request.startTime());
        entity.setEndTime(request.endTime());
        entity.setRoom(request.room());
        entity.setTrack(request.track());
    }

    /** Ensures start time is before end time; throws if not. */
    private void validateTimes(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
    }

    /** Maps a persisted entity to a response DTO. */
    private SessionResponse mapToResponse(SessionEntity entity) {
        return new SessionResponse(
            entity.getId(),
            entity.getTitle(),
            entity.getSpeaker(),
            entity.getStartTime(),
            entity.getEndTime(),
            entity.getRoom(),
            entity.getTrack()
        );
    }
}
