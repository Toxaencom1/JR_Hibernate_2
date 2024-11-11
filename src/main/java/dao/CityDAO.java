package dao;

import entity.City;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class CityDAO extends BaseDAO<City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public City getCityByName(String cityName) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Query<City> query = session.createQuery("from City c where c.cityName = :cityName",City.class);
            query.setParameter("cityName", StringUtils.capitalize(cityName.toLowerCase()));
            City city = query.uniqueResult();
            if(city == null) {
                throw new RuntimeException("Stop");
            }
            transaction.commit();
            return city;
        }
    }
}
