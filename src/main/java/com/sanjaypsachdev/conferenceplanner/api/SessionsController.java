package com.sanjaypsachdev.conferenceplanner.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sanjaypsachdev.conferenceplanner.service.SessionService;

import jakarta.validation.Valid;

/**
 * REST controller for conference session management.
 * Exposes CRUD endpoints for listing, creating, updating, and deleting sessions,
 * with optional filtering by track and speaker.
 * 
 * @author Sanjay P. Sachdev
 * @version 1.0
 * @since 2026-03-10
 */
@RestController
@RequestMapping("/api/sessions")
public class SessionsController {
    private final SessionService sessionService;

    /**
     * Creates the controller with the given session service.
     *
     * @param sessionService the service used to perform session operations
     */
    public SessionsController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    /**
     * Returns all sessions, optionally filtered by track and/or speaker.
     *
     * @param track   optional track name to filter sessions
     * @param speaker optional speaker name to filter sessions
     * @return list of sessions matching the filters (or all if no filters provided)
     */
    @GetMapping
    public List<SessionResponse> getSessions(@RequestParam Optional<String> track,
                                           @RequestParam Optional<String> speaker) {
        return sessionService.getAll(track, speaker);
    }

    /**
     * Returns a single session by its identifier.
     *
     * @param id the session ID (path variable)
     * @return the session with the given ID
     */
    @GetMapping("{id}")
    public SessionResponse getSessionById(@PathVariable Long id) {
        return sessionService.findById(id);
    }

    /**
     * Creates a new session. The request body is validated before creation.
     * Responds with 201 Created and a Location header pointing to the new session.
     *
     * @param request the session creation payload (title, description, track, speaker, etc.)
     * @return 201 with the created session in the body and Location header
     */
    @PostMapping
    public ResponseEntity<SessionResponse> addSession(@Valid @RequestBody SessionCreationRequest request) {
        SessionResponse created = sessionService.addSession(request);
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(created.id())
                        .toUri();
        return ResponseEntity.created(location).body(created);
    }

    /**
     * Updates an existing session by ID. The request body is validated.
     *
     * @param id      the session ID (path variable)
     * @param request the fields to update (title, description, track, speaker, etc.)
     * @return 200 OK with the updated session in the body
     */
    @PutMapping("{id}")
    public ResponseEntity<SessionResponse> updateSession(
            @PathVariable Long id, 
            @Valid @RequestBody SessionUpdateRequest request) {
        SessionResponse updated = sessionService.updateSession(id, request);
        return ResponseEntity.ok(updated);
    }

    /**
     * Deletes a session by ID.
     *
     * @param id the session ID (path variable)
     * @return 204 No Content on success
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}
