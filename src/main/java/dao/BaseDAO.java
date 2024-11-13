package dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@AllArgsConstructor
public class BaseDAO<T> {
    private final Class<T> clazz;

    protected SessionFactory sessionFactory;

    public T findById(int id) {
        return getCurrentSession().find(clazz, id);
    }

    public List<T> getAll() {
        return getCurrentSession().createQuery("from " + clazz.getName(), clazz).list();
    }

    public T save(T entity) {
        getCurrentSession().persist(entity);
        return entity;
    }

    public List<T> save(List<T> entity) {
        entity.forEach(this::save);
        return entity;
    }

    public T update(T entity) {
        return getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        getCurrentSession().remove(entity);
    }

    public void delete(int id) {
        getCurrentSession().remove(findById(id));
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
