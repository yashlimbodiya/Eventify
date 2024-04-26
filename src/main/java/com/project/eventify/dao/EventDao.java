package com.project.eventify.dao;

import com.project.eventify.model.Event;
import com.project.eventify.model.EventRegistration;
import com.project.eventify.model.User;
import com.project.eventify.model.UserResponseType;
import com.project.eventify.util.TransactionManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventDao extends TransactionManager {
    public Event save(Event event) {
        System.out.println("In EventDao: " + event);
        return executeTransaction(session -> {
            session.persist(event);
            return event;
        });
    }

    public Event getEvent(int id) {
        System.out.println("In EventDao: " + id);
        return executeTransaction(session -> session.get(Event.class, id));
    }

    public List<Event> getAllEvents() {
        return executeTransaction(session -> session.createQuery("FROM Event", Event.class).getResultList());
    }

    public List<Event> getEventsByCity(String city) {
        return executeTransaction(session -> session
                .createQuery("FROM Event WHERE city =: city", Event.class)
                .setParameter("city", city)
                .getResultList());
    }

    public List<Event> getEventsByCategory(String selectedCategory) {
        return executeTransaction(session -> session
                .createQuery("FROM Event WHERE category LIKE :selectedCategory", Event.class)
                .setParameter("selectedCategory", selectedCategory + "%")
                .getResultList());
    }

    public List<Event> getEventById(int id) {
        return executeTransaction(session -> session
                .createQuery("FROM Event WHERE organizer =: id", Event.class)
                .setParameter("id", id)
                .getResultList());
    }

    public List<Event> getEventsRsvpByUserId(int userId) {
        return executeTransaction(session -> {
            String hql = "SELECT e FROM Event e " +
                    "JOIN EventRegistration er ON e.eventId = er.event.eventId " +
                    "WHERE er.user.userId = :userId AND er.response = 'Accepted'";
            Query query = session.createQuery(hql, Event.class);
            query.setParameter("userId", userId);

            return query.getResultList();
        });
    }

    public int getTotalPostedEventsByOrganizer(int org_id) {
        return executeTransaction(session -> {
            Query query = session.createQuery(
                    "SELECT COUNT(e) FROM Event e WHERE e.organizer = :org_id", Event.class);
            query.setParameter("org_id", org_id);
            return Math.toIntExact((Long) query.getSingleResult());
        });
    }

    public Long getTotalRsvpOfAllEvents(int organizer) {

        return executeTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<EventRegistration> er = criteriaQuery.from(EventRegistration.class);

            criteriaQuery.select(criteriaBuilder.count(er.get("rsvpId")));

            Predicate acceptedResponse = criteriaBuilder.equal(er.get("response"), UserResponseType.Accepted);
            Subquery<Integer> eventSubquery = criteriaQuery.subquery(Integer.class);
            Root<Event> eventRoot = eventSubquery.from(Event.class);
            eventSubquery.select(eventRoot.get("eventId"));
            Predicate organizerId = criteriaBuilder.equal(eventRoot.get("userId").get("id"), organizer);
            eventSubquery.where(organizerId);

            criteriaQuery.where(criteriaBuilder.and(acceptedResponse, er.get("eventId").in(eventSubquery)));

            return (Long) session.createQuery(criteriaQuery).getSingleResult();
        });
    }

}
