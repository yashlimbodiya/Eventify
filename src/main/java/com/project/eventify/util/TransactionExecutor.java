package com.project.eventify.util;

import org.hibernate.Session;

public interface TransactionExecutor<T> {
     T execute(Session session);
}
