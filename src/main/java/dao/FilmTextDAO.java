package dao;

import entity.FilmText;
import org.hibernate.SessionFactory;

public class FilmTextDAO extends BaseDAO<FilmText> {
    public FilmTextDAO(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
