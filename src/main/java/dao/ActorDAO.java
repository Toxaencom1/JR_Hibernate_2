package dao;

import entity.Actor;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class ActorDAO extends BaseDAO<Actor> {
    public ActorDAO(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }

    public Actor findActorByNameAndSurname(String name, String surname) {
        Query<Actor> query = sessionFactory.getCurrentSession()
                .createQuery("from Actor a where lower(a.firstName) = :name and lower(a.lastName) = :surname", Actor.class);
        query.setParameter("name", name.toLowerCase());
        query.setParameter("surname", surname.toLowerCase());
        return query.getSingleResult();
    }
}
