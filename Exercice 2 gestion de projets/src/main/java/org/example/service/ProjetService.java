package org.example.service;

import org.example.entities.EmployeTache;
import org.example.entities.Projet;
import org.example.entities.Tache;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProjetService extends AbstractFacade<Projet> {

    public ProjetService() {
        super(Projet.class);
    }

    public List<Tache> findTachesByProjet(Projet projet) {
        Session session = null;
        Transaction tran = null;
        List<Tache> taches = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            taches = session.createNamedQuery("findTachesByProjet", Tache.class)
                    .setParameter("projet", projet)
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

    public List<EmployeTache> findTachesRealiseesByProjet(Projet projet) {
        Session session = null;
        Transaction tran = null;
        List<EmployeTache> taches = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            taches = session.createNamedQuery("findTachesRealiseesByProjet", EmployeTache.class)
                    .setParameter("projet", projet)
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