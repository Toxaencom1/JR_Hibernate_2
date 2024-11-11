package dao;

import entity.Address;
import entity.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AddressDAO extends BaseDAO<Address> {
    public AddressDAO(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }

    public Address getAddressByName(String addressName) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Query<Address> query = session.createQuery("from Address c where LOWER(c.address) = LOWER(:addressName)",Address.class);
            query.setParameter("addressName", addressName);
            Address address = query.uniqueResult();
            if(address == null) {
                throw new RuntimeException("Stop");
            }
            transaction.commit();
            return address;
        }
    }
}
