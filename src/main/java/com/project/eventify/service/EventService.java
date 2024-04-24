package com.project.eventify.service;

import com.project.eventify.dao.EventDao;
import com.project.eventify.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EventService {

    private final EventDao eventDao;

    @Autowired
    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public Event saveEvent(Event event) {
        System.out.println("In EventService: " + event);
        eventDao.save(event);
        return event;
    }

    public List<Event> getAllEvents() {
        System.out.println("In eventService :: getAllEvents");
        return eventDao.getAllEvents();
    }
    public List<Event> getEventsByCity(String city) {
        System.out.println("In eventService :: getEventsByCity");
        return eventDao.getEventsByCity(city);
    }

    public List<Event> getEventsByCategory(String category) {
        System.out.println("In eventService :: getEventsByCategory");
        return eventDao.getEventsByCategory(category);
    }

    public List<Event> getEventsById(int id) {
        System.out.println("In eventService :: getEventsById");
        List<Event> event = eventDao.getEventById(id);
        System.out.println("getEventsById: " + Arrays.toString(event.toArray()));
        return event;
    }

    public List<Event> getEventsRsvpByUserId(int id) {
        System.out.println("In EventService :: getEventsRsvpByUserId");
        return eventDao.getEventsRsvpByUserId(id);
    }

    public Long getTotalRsvpOfAllEvents(int org_id) {
        System.out.println("In EventService :: getTotalRsvpOfAllEvents");
        return eventDao.getTotalRsvpOfAllEvents(org_id);
    }

    public int getTotalPostedEventsByOrganizer(int org_id) {
        System.out.println("In EventService :: getTotalPostedEventsByOrganizer");
        return eventDao.getTotalPostedEventsByOrganizer(org_id);
    }
}
