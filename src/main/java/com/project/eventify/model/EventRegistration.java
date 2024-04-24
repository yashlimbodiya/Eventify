package com.project.eventify.model;

import jakarta.persistence.*;

@Entity
@Table(name = "event_registration")
public class EventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rsvp_id")
    private int rsvpId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "event_id")
    private int eventId;

    @Enumerated(EnumType.STRING)
    @Column(name = "response")
    private UserResponseType response;
    public int getRsvpId() {
        return rsvpId;
    }

    public void setRsvpId(int rsvpId) {
        this.rsvpId = rsvpId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public UserResponseType getResponse() {
        return response;
    }

    public void setResponse(UserResponseType response) {
        this.response = response;
    }

    public EventRegistration() {
    }

    public EventRegistration(int rsvpId, int userId, int eventId, UserResponseType response) {
        this.rsvpId = rsvpId;
        this.userId = userId;
        this.eventId = eventId;
        this.response = response;
    }
}
