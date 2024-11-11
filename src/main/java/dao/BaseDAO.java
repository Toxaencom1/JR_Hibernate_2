package dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

@AllArgsConstructor
public class BaseDAO<T> {
    private final Class<T> clazz;

    protected SessionFactory sessionFactory;

    public T getById(int id) {
        try (Session session = sessionFactory.openSession()){
            T object = session.find(clazz, id);
            return object;
        }
    }

    public List<T> getAll() {
        try (Session session = sessionFactory.openSession()){
            List<T> list = session.createQuery("from " + clazz.getName(), clazz).list();
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
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        }
    }

    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(getById(id));
            transaction.commit();
        }
    }
}
