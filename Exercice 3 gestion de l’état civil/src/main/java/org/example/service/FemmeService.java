package org.example.service;

import org.example.entities.Femme;
import org.example.entities.Mariage;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Date;
import java.util.List;

public class FemmeService extends AbstractFacade<Femme>{

    public FemmeService() {
        super(Femme.class);
    }

    public int findEnfants(int f_id, Date d1, Date d2) {
        Session session = null;
        Transaction tran = null;
        int nbEnf = 0;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            Object result = session.createNativeQuery(
                            "select sum(m.nbrEnfant) as total from Mariage m where m.femme = :f_id and m.DateDebut between :d1 and :d2"
                    )
                    .setParameter("f_id", f_id)
                    .setParameter("d1", d1)
                    .setParameter("d2", d2)
                    .getSingleResult();

            if (result != null) {
                nbEnf = ((Number) result).intValue();
            }

            tran.commit();

        } catch (Exception h) {
            if (tran != null) tran.rollback();
            h.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
        return nbEnf;
    }

    public List<Femme> findFemmesMarieesAuMoinsDeux() {
        Session session = null;
        Transaction tran = null;
        List<Femme> femmes = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            femmes = session.createNamedQuery("findFemmesMarieesAuMoinsDeux", Femme.class)
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
        return femmes;
    }


}