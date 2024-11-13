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
                .createQuery("select r from Rental r left join r.inventory i where i.film.id = :filmId", Rental.class);
        query1.setParameter("filmId", filmId);
        List<Rental> rentalFilmResultList = query1.getResultList();

        if (rentalFilmResultList.isEmpty()) {
            Query<Inventory> query2 = sessionFactory.getCurrentSession()
                    .createQuery("from Inventory i where i.film.id = :filmId", Inventory.class);
            query2.setParameter("filmId", filmId);
            query2.setMaxResults(1);
            return query2.getSingleResult();
        } else {
            List<Inventory> notNullList = rentalFilmResultList.stream()
                    .filter(rental -> rental.getReturnDate() != null)
                    .map(Rental::getInventory).toList();
            List<Inventory> nullList = rentalFilmResultList.stream()
                    .filter(rental -> rental.getReturnDate() == null)
                    .map(Rental::getInventory).toList();

            if (nullList.isEmpty()) {
                return notNullList.get(0);
            } else if (!notNullList.isEmpty()) {
                for (Inventory inventory : notNullList) {
                    if (!nullList.contains(inventory)) {
                        return inventory;
                    }
                }
            }
        }
        return null;
    }
}
