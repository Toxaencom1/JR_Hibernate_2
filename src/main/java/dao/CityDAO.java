package dao;

import entity.City;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Optional;


public class CityDAO extends BaseDAO<City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public Optional<City> getCityByName(String cityName) {
        Query<City> query = getCurrentSession().createQuery("from City c where c.cityName = :cityName", City.class);
        query.setParameter("cityName", StringUtils.capitalize(cityName.toLowerCase()));
        return Optional.of(query.getSingleResult());
    }
}
