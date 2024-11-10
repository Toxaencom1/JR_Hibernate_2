package dao;

import entity.Film;
import org.hibernate.SessionFactory;

public class FilmDAO extends BaseDAO<Film> {
    public FilmDAO(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }
}
