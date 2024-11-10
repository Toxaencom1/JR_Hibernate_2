package dao;

import entity.Customer;
import org.hibernate.SessionFactory;


public class CustomerDAO extends BaseDAO<Customer> {

    public CustomerDAO(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
