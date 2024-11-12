package dao;

import entity.Inventory;
import entity.Rental;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class InventoryDAO extends BaseDAO<Inventory> {
    public InventoryDAO(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }

    public Inventory pickAvailableInventoryMovie(int filmId) {
        Query<Rental> query1 = sessionFactory.getCurrentSession()
                .createQuery("select r from Rental r left join r.inventory i where i.film.id = :filmId AND r.returnDate IS NOT NULL", Rental.class);
        query1.setParameter("filmId", filmId);
        List<Rental> resultListNotNull = query1.getResultList();

        Query<Rental> query2 = sessionFactory.getCurrentSession()
                .createQuery("select r from Rental r left join r.inventory i where i.film.id = :filmId AND r.returnDate IS NULL", Rental.class);
        query2.setParameter("filmId", filmId);
        List<Rental> resultListNull = query2.getResultList();

        List<Inventory> inventoryListNotNull = resultListNotNull.stream().map(Rental::getInventory).toList();

        if (resultListNull.isEmpty()){
            return inventoryListNotNull.get(0);
        } else if (!resultListNotNull.isEmpty()) {
            List<Inventory> inventoryListNull = resultListNull.stream().map(Rental::getInventory).toList();
            for (Inventory inventory : inventoryListNotNull) {
                if (!inventoryListNull.contains(inventory)) {
                    return inventory;
                }
            }
        }
        return null;
    }
}
