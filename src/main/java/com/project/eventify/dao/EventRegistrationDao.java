package com.project.eventify.dao;

import com.project.eventify.model.Event;
import com.project.eventify.model.EventRegistration;
import com.project.eventify.util.TransactionManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Component
public class EventRegistrationDao extends TransactionManager {
    public EventRegistration saveEventRegistration(EventRegistration er) {
        System.out.println("In EventRegistrationDao: " + er);
        return executeTransaction(session -> {
            session.persist(er);
            return er;
        });
    }

    public List<EventRegistration> getRsvpDetails(int userId, int eventId) {
        System.out.println("In EventRegistrationDao:: getRsvpDetails " + userId + ", " + eventId);
        return executeTransaction(session -> session
                .createQuery("FROM EventRegistration WHERE userId =: userId AND eventId =: eventId", EventRegistration.class)
                .setParameter("userId", userId)
                .setParameter("eventId", eventId)
                .getResultList());
    }

    public EventRegistration updateRsvpDetails(EventRegistration er) {
        System.out.println("In EventRegistrationDao:: updateRsvpDetails " + er);
        return executeTransaction(session -> {
            session.merge(er);
            return er;
        });
    }

    public List<EventRegistration> getAllRsvp(int id) {
        System.out.println("In EventRegistrationDao:: getAllRsvp = " + id);
        return executeTransaction(session -> session.createQuery("FROM EventRegistration WHERE userId =: id", EventRegistration.class)
                .setParameter("id",id)
                .getResultList());
    }

}
