package org.example.service;

import org.example.entities.Employe;
import org.example.entities.Projet;
import org.example.entities.Tache;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeService extends AbstractFacade<Employe> {

    public EmployeService() {
        super(Employe.class);
    }

    public List<Tache> findTachesByEmploye(Employe employe) {
        Session session = null;
        Transaction tran = null;
        List<Tache> taches = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            taches = session.createNamedQuery("findTachesByEmploye", Tache.class)
                    .setParameter("employe", employe)
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

    public List<Projet> findProjetsByEmploye(Employe employe) {
        Session session = null;
        Transaction tran = null;
        List<Projet> projets = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            projets = session.createNamedQuery("findProjetsByEmploye", Projet.class)
                    .setParameter("employe", employe)
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
        return projets;
    }


}