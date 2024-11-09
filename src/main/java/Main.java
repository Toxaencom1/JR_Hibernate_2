import config.DataBaseConfig;
import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        DataBaseConfig config = new DataBaseConfig();
        try (SessionFactory sf = config.buildSessionFactory();
             Session session = sf.openSession()){
            Transaction transaction = session.beginTransaction();
            Rental object = session.find(Rental.class, 1);

            System.out.println(object);
            transaction.commit();
        }
    }
}
