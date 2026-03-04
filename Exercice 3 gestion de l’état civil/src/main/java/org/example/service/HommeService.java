package org.example.service;

import org.example.entities.Homme;
import org.example.entities.Femme;
import org.example.entities.Mariage;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class HommeService extends AbstractFacade<Homme>{

    public HommeService() {
        super(Homme.class);
    }

    public List<Femme> findEpouses(int h_id, Date d1, Date d2) {
        Session session = null;
        Transaction tran = null;
        List<Femme> epouses = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            epouses = session.createNamedQuery("findEpouses", Femme.class)
                    .setParameter("h_id", h_id)
                    .setParameter("d1", d1)
                    .setParameter("d2", d2)
                    .list();

            tran.commit();
        }catch(Exception h) {
            if(tran != null) {
                tran.rollback();
            }
            h.printStackTrace();
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return epouses;
    }

    public long getNbrHommesMariesQuatreFemmes(Date d1, Date d2) {
        Session session = null;
        Transaction tran = null;
        long count = 0;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            // FROM Mariage m
            Root<Mariage> root = cq.from(Mariage.class);

            // SELECT m.homme
            cq.select(cb.countDistinct(root.get("homme")))
                    .where(cb.between(root.get("dateDebut"), d1, d2))  // WHERE m.dateDebut BETWEEN d1 AND d2

                    .groupBy(root.get("homme"))                 // GROUP BY m.homme
                    .having(cb.equal(cb.count(root.get("femme")), 4));// HAVING COUNT(m.femme) = 4

            // COUNT(*) des résultats = nombre d'hommes
            List<Long> hommes = session.createQuery(cq).getResultList();

            count = hommes.size();

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

        return count;
    }

    public List<Mariage> findMariagesByHomme(int h_id) {
        Session session = null;
        Transaction tran = null;
        List<Mariage> mariages = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            mariages = session.createNamedQuery("findMariagesByHomme", Mariage.class)
                    .setParameter("h_id", h_id)
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

        return mariages;
    }

    public void afficherMariagesByHomme(int h_id) {
        List<Mariage> mariages = findMariagesByHomme(h_id);

        if (mariages == null || mariages.isEmpty()) {
            System.out.println("Aucun mariage trouvé pour l'homme avec id : " + h_id);
            return;
        }

        for (Mariage m : mariages) {
            System.out.println("==============================");
            System.out.println("Épouse  : " + m.getFemme().getPrenom() + " " + m.getFemme().getNom());
            System.out.println("Début   : " + m.getDateDebut());
            System.out.println("Fin     : " + m.getDateFin());
            System.out.println("Enfants : " + m.getNbrEnfant());
        }
        System.out.println("==============================");
    }
}