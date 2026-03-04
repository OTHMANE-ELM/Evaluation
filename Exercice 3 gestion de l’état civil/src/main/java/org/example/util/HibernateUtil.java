package org.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionfactory;

    static{
        try{
            sessionfactory = new Configuration().configure().buildSessionFactory();
        }catch(Throwable ex) {
            System.err.printf("Initial session factory creation failed" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionfactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}