package com.project.eventify.util;

import com.project.eventify.config.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;

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
            System.out.println("Exception in TransactionManager: " + e.getMessage());
            System.out.println("Exception in TransactionManager Stack: " + Arrays.toString(e.getStackTrace()));
            if (transaction != null) {
                transaction.rollback();
            }
            result = null;
        }
        return result;
    }



}
