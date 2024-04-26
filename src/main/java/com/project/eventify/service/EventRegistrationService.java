package com.project.eventify.service;

import com.project.eventify.dao.EventRegistrationDao;
import com.project.eventify.model.Event;
import com.project.eventify.model.EventRegistration;
import com.project.eventify.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRegistrationService {

    private final EventRegistrationDao eventRegistrationDao;

    @Autowired
    public EventRegistrationService(EventRegistrationDao eventRegistrationDao) {
        this.eventRegistrationDao = eventRegistrationDao;
    }

    public EventRegistration saveEvent(EventRegistration er, User user, Event event) {
        System.out.println("In EventRegistrationService: " + er);
        return eventRegistrationDao.saveEventRegistration(er, user, event);
    }

    public List<EventRegistration> getRsvpDetails(User userId, Event eventId) {
        System.out.println("In EventRegistrationService :: getRsvpDetails");
        return eventRegistrationDao.getRsvpDetails(userId, eventId);
    }

    public EventRegistration updateRsvpDetails(EventRegistration er) {
        System.out.println("In EventRegistrationService:: updateRsvpDetails " + er);
        return eventRegistrationDao.updateRsvpDetails(er);
    }

    public List<EventRegistration> getAllRsvp(int id) {
        System.out.println("In EventRegistration :: getAllRsvp = " + id);
        return eventRegistrationDao.getAllRsvp(id);
    }


}
