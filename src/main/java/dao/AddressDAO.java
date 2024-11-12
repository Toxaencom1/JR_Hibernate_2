package dao;

import entity.Address;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Optional;

public class AddressDAO extends BaseDAO<Address> {
    public AddressDAO(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }

    public Optional<Address> getAddressByName(String addressName) {
        Query<Address> query = getCurrentSession().createQuery("from Address c where LOWER(c.address) = LOWER(:addressName)", Address.class);
        query.setParameter("addressName", addressName);
        return Optional.of(query.getSingleResult());
    }
}
