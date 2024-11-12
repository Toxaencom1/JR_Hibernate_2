package dao;

import entity.Rental;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class RentalDAO extends BaseDAO<Rental> {
    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public boolean checkTheFilmForAvailability(int filmId) {
        Query<Rental> query = sessionFactory.getCurrentSession()
                .createQuery("select r from Rental r left join r.inventory i where i.film.id = :filmId AND r.returnDate IS NULL", Rental.class);
        query.setParameter("filmId", filmId);
        List<Rental> resultList = query.getResultList();
        return resultList.isEmpty();
    }
}
