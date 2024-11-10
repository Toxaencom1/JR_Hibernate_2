package dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@AllArgsConstructor
public class BaseDAO<T> {
    private final Class<T> clazz;

    private SessionFactory sessionFactory;

    public T getById(int id) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            T object = session.find(clazz, id);
            transaction.commit();
            return object;
        }
    }

    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            List<T> list = session.createQuery("from " + clazz.getName(), clazz).list();
            transaction.commit();
            return list;
        }
    }

    public T save(T entity) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        }
    }

    public T update(T entity) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            T merge = session.merge(entity);
            transaction.commit();
            return merge;
        }
    }

    public void delete(T entity) {
        sessionFactory.getCurrentSession().remove(entity);
    }

    public void delete(int id) {
        sessionFactory.getCurrentSession().remove(getById(id));
    }
}
