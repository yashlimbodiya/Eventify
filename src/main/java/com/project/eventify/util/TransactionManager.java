package com.project.eventify.util;

import com.project.eventify.config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionManager  {
    protected <T> T executeTransaction(TransactionExecutor<T> executor) {
        T result = null;
        Transaction transaction = null;
        try {
            Session session = HibernateConfig.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            result = executor.execute(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            result = null;
        }
        return result;
    }



}
