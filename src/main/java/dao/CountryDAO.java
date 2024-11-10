package dao;

import entity.Country;
import org.hibernate.SessionFactory;

public class CountryDAO extends BaseDAO<Country> {
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }
}
