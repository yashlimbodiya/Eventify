package com.project.eventify.dao;

import com.project.eventify.model.Event;
import com.project.eventify.model.EventRegistration;
import com.project.eventify.model.User;
import com.project.eventify.model.UserResponseType;
import com.project.eventify.util.TransactionManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;


@Component
public class EventRegistrationDao extends TransactionManager {
    public EventRegistration saveEventRegistration(EventRegistration er, User user, Event event) {
        System.out.println("In EventRegistrationDao: saveEventRegistration" + er);
        return executeTransaction(session -> {
            Event mergedEvent = session.merge(event);
            er.setUser(user);
            er.setEvent(mergedEvent);
            session.persist(er);
            return er;
        });
    }

    public List<EventRegistration> getRsvpDetails(User user, Event event) {
        System.out.println("In EventRegistrationDao:: getRsvpDetails " + event + ", " + event);
        return executeTransaction(session -> session
                .createQuery("FROM EventRegistration WHERE user =: user AND event =: event", EventRegistration.class)
                .setParameter("user", user)
                .setParameter("event", event)
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
        return executeTransaction(session -> session.createQuery("FROM EventRegistration WHERE user.userId =: id", EventRegistration.class)
                .setParameter("id",id)
                .getResultList());
    }

    public List<Object[]> getListOfUsersRsvpedEvent(int eventId) {
        System.out.println("In EventRegistrationDao:: getListOfUsersRsvpedEvent = " + eventId);
        return executeTransaction(session -> {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            Join<User, EventRegistration> eventRegistrationJoin = userRoot.join("eventRegistrations", JoinType.INNER);
            Join<EventRegistration, Event> eventJoin = eventRegistrationJoin.join("event", JoinType.INNER);

            criteriaQuery.multiselect(
                    userRoot.get("firstName"),
                    userRoot.get("lastName"),
                    userRoot.get("email"),
                    userRoot.get("userId")
            );

            Predicate eventIdMatch = criteriaBuilder.equal(eventJoin.get("eventId"), eventId);
            Predicate acceptedResponse = criteriaBuilder.equal(eventRegistrationJoin.get("response"), UserResponseType.Accepted);
            criteriaQuery.where(eventIdMatch, acceptedResponse);

            return session.createQuery(criteriaQuery).getResultList();

        });


    }

}
