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

        Animal animal = new Animal();
        animal.setId(5);
        animal.setName("Cow");


       session.save(animal);

        transaction.commit();

//      animal = (Animal) session.get(Animal.class,1);
//        System.out.println(animal);
        session.close();
        sessionFactory.close();
    }
}
