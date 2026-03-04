package org.example.service;

import org.example.dao.IDao;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public abstract class AbstractFacade<T> implements IDao<T> {

    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public boolean create(T o) {
        Session session = null;
        Transaction tran = null;
        boolean etat = false;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            session.save(o);
            tran.commit();
            etat = true;
        } catch(HibernateException h) {
            if(tran != null) {
                tran.rollback();
            }
            h.printStackTrace();
        } finally {
            if(session!=null) {
                session.close();
            }
        }
        return etat;
    }

    @Override
    public boolean update(T o) {
        Session session = null;
        Transaction tran = null;
        boolean etat = false;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            session.merge(o);
            tran.commit();
            etat = true;
        }catch(HibernateException h) {
            if(tran != null) {
                tran.rollback();
            }
            h.printStackTrace();
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return etat;
    }

    @Override
    public boolean delete(T o) {
        Session session = null;
        Transaction tran = null;
        boolean etat = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            T attached = (T) session.merge(o); // rattacher à la session
            session.remove(attached);// mieux faire delete(o)
            tran.commit();
            etat = true;
        }catch(HibernateException h) {
            if(tran != null) {
                tran.rollback();
            }
            h.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return etat;
    }

    @Override
    public T findById(int id) {
        Session session = null;
        Transaction tran = null;
        T obj = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            obj = session.find(entityClass, id);
            tran.commit();
        }catch(HibernateException h) {
            if(tran != null) {
                tran.rollback();
            }
            h.printStackTrace();
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return obj;
    }

    @Override
    public List<T> findAll() {
        Session session = null;
        Transaction tran = null;
        List<T> list = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tran = session.beginTransaction();

            list = session.createQuery("from " + entityClass.getSimpleName(), entityClass).list();
            tran.commit();
        }catch(HibernateException h) {
            if(tran != null) {
                tran.rollback();
            }
            h.printStackTrace();
        }finally {
            if(session != null) {
                session.close();
            }
        }
        return list;
    }
}
