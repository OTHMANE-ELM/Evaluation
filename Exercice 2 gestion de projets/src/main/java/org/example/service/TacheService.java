package org.example.service;

import org.example.entities.Tache;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class TacheService extends AbstractFacade<Tache> {

    public TacheService() {
        super(Tache.class);
    }

    public List<Tache> findTachesExpensives() {
        Session session = null;
        Transaction tran = null;
        List<Tache> taches = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            taches = session.createNamedQuery("findTachesExpensives", Tache.class)
                    .setParameter("prix", 1000.0)
                    .list();

            tran.commit();

        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return taches;
    }

    public List<Tache> findTachesBetweenDates(Date d1, Date d2) {
        Session session = null;
        Transaction tran = null;
        List<Tache> taches = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            taches = session.createNamedQuery("findTachesBetweenDates", Tache.class)
                    .setParameter("d1", d1)
                    .setParameter("d2", d2)
                    .list();

            tran.commit();

        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return taches;
    }
}