package com.example;

import com.example.entity.Employee;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Example operation: create a new entity instance
            Employee entity = new Employee();
            entity.setId(1L);
            // Set other fields if necessary

            session.save(entity);

            transaction.commit();
            System.out.println("Entity saved successfully.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
