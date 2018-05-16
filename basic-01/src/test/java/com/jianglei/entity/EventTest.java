package com.jianglei.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * @author jianglei
 * @since 2018/5/16
 */
public class EventTest {

    private SessionFactory sessionFactory;

    @Before
    public void init() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }

    }


    @Test
    public void test01() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Event event = new Event();
        event.setId(1L);
        event.setDate(new Date());
        event.setTitle("event title");
        session.save(event);

        session.getTransaction().commit();
    }


    @After
    public void destroy() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }



}
