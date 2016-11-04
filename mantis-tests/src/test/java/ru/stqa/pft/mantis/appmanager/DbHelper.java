package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.User;

import java.util.List;

/**
 * Created by aleksandr.petrov on 04.11.16.
 */
public class DbHelper {

    private final SessionFactory sessionFactory;


    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public User user() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> result = session.createQuery("from User where id > 1").list();
        User user = result.stream().iterator().next();

        session.getTransaction().commit();
        session.close();
        return user;
    }
}
