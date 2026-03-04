package org.example.service;

import org.example.entities.Categorie;
import org.example.entities.Commande;
import org.example.entities.Produit;
import org.example.util.HibernateUtil;
//import org.hibernate.HibernateException;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProduitService extends AbstractFacade<Produit>{

    public ProduitService() {
        super(Produit.class);
    }

    public List<Produit> findByCategorie(Categorie categorie) {
        Session session = null;
        Transaction tran = null;
        List<Produit> produits = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            produits = session.createNamedQuery("findByCategorie", Produit.class)
                    .setParameter("categorie", categorie)
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
        return produits;
    }

    public List<Produit> findBetweenDates(Date d1, Date d2) {
        Session session = null;
        Transaction tran = null;
        List<Produit> produits = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            produits = session.createNamedQuery("findBetweenDates", Produit.class)
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
        return produits;
    }

    public List<Produit> findByCommande(Commande commande) {
        Session session = null;
        Transaction tran = null;
        List<Produit> produits = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            produits = session.createNamedQuery("findByCommande", Produit.class)
                    .setParameter("commande", commande)
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
        return produits;
    }

    public List<Produit> findByPrix(double prix) {
        Session session = null;
        Transaction tran = null;
        List<Produit> produits = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            produits = session.createNamedQuery("findByPrix", Produit.class)
                    .setParameter("prix", prix)
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
        return produits;
    }

    public List<Produit> findByCriteria(Categorie categorie, Double prixMin, Double prixMax) {
        Session session = null;
        Transaction tran = null;
        List<Produit> produits = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Produit> cq = cb.createQuery(Produit.class);
            Root<Produit> root = cq.from(Produit.class);

            List<Predicate> predicates = new ArrayList<>();

            if(categorie != null) {
                predicates.add(cb.equal(root.get("categorie"), categorie));
            }

            if(prixMin != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("prix"), prixMin));
            }

            if(prixMax != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("prix"), prixMax));
            }

            cq.select(root).where(predicates.toArray(new Predicate[0]));
            produits = session.createQuery(cq).getResultList();

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
        return produits;
    }

    public List<Produit> findPaginated(int page, int size) {
        Session session = null;
        Transaction tx = null;
        List<Produit> produits = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            // sécuriser les valeurs
            if (page < 1) page = 1;
            if (size < 1) size = 10;

            int firstResult = (page - 1) * size;

            Criteria criteria = session.createCriteria(Produit.class);
            criteria.addOrder(Order.asc("id")); // ou "nom"
            criteria.setFirstResult(firstResult); // offset
            criteria.setMaxResults(size);         // limit

            produits = criteria.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return produits;
    }

    public List<Produit> findByNomCriteria(String motCle) {
        Session session = null;
        Transaction tx = null;
        List<Produit> produits = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Produit.class);
            criteria.add(Restrictions.ilike("reference", motCle, MatchMode.ANYWHERE));
            // si ton champ s'appelle "designation", remplace "nom" par "designation"

            produits = criteria.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return produits;
    }

}