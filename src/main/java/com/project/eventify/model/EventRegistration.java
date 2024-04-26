package com.project.eventify.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;


@Entity
@Table(name = "event_registration")
public class EventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rsvp_id")
    private int rsvpId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Event event;

    @Enumerated(EnumType.STRING)
    @Column(name = "response")
    private UserResponseType response;
    public int getRsvpId() {
        return rsvpId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setRsvpId(int rsvpId) {
        this.rsvpId = rsvpId;
    }

    public UserResponseType getResponse() {
        return response;
    }

    public void setResponse(UserResponseType response) {
        this.response = response;
    }

    public EventRegistration() {
    }

    @Override
    public String toString() {
        return "EventRegistration{" +
                "rsvpId=" + rsvpId +
                ", user=" + user +
                ", event=" + event +
                ", response=" + response +
                '}';
    }

    public EventRegistration(int rsvpId, User user, Event event, UserResponseType response) {
        this.rsvpId = rsvpId;
        this.event = event;
        this.user = user;
        this.response = response;
    }
}
