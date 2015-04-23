package dao;

import model.Category;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 06.04.2015.
 */
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext(unitName = "bookshopUnit")
    private static EntityManager em;

    private static EntityTransaction tx;

    static {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("bookshopUnit");
        System.out.println(entityManagerFactory.getClass().getSimpleName());
        em = entityManagerFactory.createEntityManager();
        System.out.println("EntityManager Created : " + em);
        tx = em.getTransaction();
    }

    @Override
    public Category getCategoryByName(String name) {
        List<Category> allCategories = em.createQuery(
                "SELECT a FROM Category a WHERE a.categoryName = '"
                        + name + "'", Category.class).getResultList();
        if (allCategories != null) {
            return allCategories.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Category getCategoryById(Long id) {
        Category category = em.find(Category.class, id);
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> allCategories = em.createQuery("SELECT a FROM Category a",
                Category.class).getResultList();
        return allCategories;
    }

    @Override
    public Serializable createCategory(Category category) {
        tx.begin();
        em.persist(category);
        tx.commit();
        return category.getCategoryId();
    }

    @Override
    public void removeCategory(Category category) {
        tx.begin();
        em.remove(category);
        tx.commit();
    }

    @Override
    public Category updateCategoryName(Category category, String newName) {
        tx.begin();
        category.setCategoryName(newName);
        em.merge(category);
        tx.commit();
        return category;
    }

    @Override
    public Category updateCategory(Category category) {
        tx.begin();
        em.merge(category);
        tx.commit();
        return category;
    }
}
