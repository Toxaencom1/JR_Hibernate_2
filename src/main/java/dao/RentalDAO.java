package dao;

import entity.Rental;
import org.hibernate.SessionFactory;

public class RentalDAO extends BaseDAO<Rental> {
    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }
}
