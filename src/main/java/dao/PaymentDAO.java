package dao;

import entity.Payment;
import org.hibernate.SessionFactory;

public class PaymentDAO extends BaseDAO<Payment> {
    public PaymentDAO(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
