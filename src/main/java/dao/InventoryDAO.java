package dao;

import entity.Inventory;
import org.hibernate.SessionFactory;

public class InventoryDAO extends BaseDAO<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
