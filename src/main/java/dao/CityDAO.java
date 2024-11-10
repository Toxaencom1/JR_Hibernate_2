package dao;

import entity.City;
import org.hibernate.SessionFactory;

public class CityDAO extends BaseDAO<City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}
