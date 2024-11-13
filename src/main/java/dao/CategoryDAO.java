package dao;

import entity.Category;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CategoryDAO extends BaseDAO<Category> {
    public CategoryDAO(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }

    public Category findByName(String categoryName) {
        Query<Category> query = sessionFactory.getCurrentSession()
                .createQuery("from Category c where lower(c.name) = :categoryName", Category.class);
        query.setParameter("categoryName", categoryName.toLowerCase());

        return query.getSingleResult();
    }
}
