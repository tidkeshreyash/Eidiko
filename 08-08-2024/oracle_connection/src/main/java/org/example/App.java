package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Laptop laptop = new Laptop();
        laptop.setId(101);
        laptop.setLname("Dwell");


        Student student = new Student();
        student.setRollNo(1);
        student.setName("SHREYASH");
        student.setMarks(90);
        student.setLaptop(laptop);



        session.save(student);
        session.save(laptop);


        transaction.commit();
        sessionFactory.close();
        session.close();



    }
}
