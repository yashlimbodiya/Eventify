package com.project.eventify.dao;

import com.project.eventify.model.User;
import com.project.eventify.util.TransactionManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserDao extends TransactionManager {
    public User save(User user) {
        return executeTransaction(session -> {
            session.persist(user);
            return user; // Success
        });
    }
    public User getById(int id) {
        return executeTransaction(session -> session.get(User.class, id));
    }

    public User getByEmail(String email) {
        return executeTransaction(session -> {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> root = query.from(User.class);
            query.select(root).where(builder.equal(root.get("email"), email));
            System.out.println("GET BY EMAIL");
            return session.createQuery(query).uniqueResult();
        });
    }

    public List<User> getAllUser() {
        return executeTransaction(session -> session.createQuery("FROM User", User.class).getResultList());
    }
}
