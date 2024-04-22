package com.therotherithethethe.dao;

import com.therotherithethethe.entity.FinalEntity;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GenericDAO<T extends FinalEntity> implements DAO<T> {

    private final String tableName;
    private final SessionFactory sessionFactory;
    private final Class<? extends FinalEntity> clazz;

    public GenericDAO(String tableName, SessionFactory sessionFactory, Class<T> clazz) {
        this.tableName = tableName;
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    ///don't know how to fix. don't know full knowledge of using generics and gpt talks shit
    @Override
    public List<T> findBy(String column, Object value) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<T> result = (List<T>) sessionFactory.getCurrentSession()
                .createQuery(STR."""
            from \{tableName} as t
            where t.\{column} = ?1
            """, clazz)
                .setParameter(1, value)
                .getResultList();
            transaction.commit();
            return result;
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<T> findAll() {
        return (List<T>) sessionFactory.getCurrentSession()
            .createQuery(STR."""
            from \{tableName}
            """, clazz)
            .getResultList();
    }

    @Override
    public boolean save(T entity) {
        if(Objects.isNull(entity.getId())) {
            entity.setId(UUID.randomUUID());
            return add(entity);
        }
        return update(entity);
    }


    @Override
    public boolean add(T entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex.getMessage());
            return false;
        }

    }

    @Override
    public boolean update(T entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
            return true;
        }
        catch (Exception _) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        }
    }

    @Override
    public boolean remove(T entity) {
        try {
            sessionFactory.getCurrentSession().persist(entity);
            return true;
        } catch (HibernateException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public String getColumns() {
        return null;
    }
}
