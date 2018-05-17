package com.jianglei.entity;

import org.hamcrest.CoreMatchers;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author jianglei
 * @since 2018/5/16
 */
public class EventTest {

    private SessionFactory sessionFactory;

    @Before
    public void init() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // 用hibernate.cfg.xml配制
                .build();
        try {
            // 一人应用初始化一个SessionFactory就可以了，初始化SessionFactory很消耗资源
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // 如果初始化SessionFactory时出现了问题，手动消毁registry
            StandardServiceRegistryBuilder.destroy( registry );
        }

    }


    /**
     * 测试保存一个实体
     */
    @Test
    public void testSave() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Event event = new Event();
        event.setDate(new Date());
        event.setTitle("event title");
        session.save(event);

        session.getTransaction().commit();
    }

    /**
     * 测试获取一个实体
     */
    @Test
    public void testGet() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Event event = session.get(Event.class, 1L);
        Assert.assertEquals("event title", event.getTitle());

        session.getTransaction().commit();
    }

    /**
     * 测试更新一个实体
     */
    @Test
    public void testUpdate() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Event event = session.get(Event.class, 1L);
        event.setTitle("title update");
        event.setDate(new Date());

        session.flush();
        session.clear();

        event = session.get(Event.class, 1L);
        Assert.assertEquals("title update", event.getTitle());

        session.getTransaction().commit();
    }

    /**
     * 测试用HQL查询实体
     */
    @Test
    public void testQuery() {
        Session session = sessionFactory.openSession();
        List<Event> events = session.createQuery("select e from Event e", Event.class)
                .getResultList();
        Assert.assertFalse(events.isEmpty());

        Event event = session.find(Event.class, 1L);
        Assert.assertThat(events, CoreMatchers.hasItem(event));
    }

    /**
     * 测试删除一个实体
     */
    @Test
    public void testDelete() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Event event = session.get(Event.class, 1L);
        session.delete(event);

        session.getTransaction().commit();
    }


    /**
     * 测试结束后，关闭sessionFactory
     */
    @After
    public void destroy() {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

}
