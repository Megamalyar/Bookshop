package dao;

import model.Book;
import model.Category;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 17.05.2015.
 */
@Repository
public class HibernateCategoryDao implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Category getCategoryByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        SQLQuery q = session.createSQLQuery("select * from category_table where category_name=:name");
        q.addEntity(Category.class);
        q.setParameter("name", name);
        Category category = (Category) q.uniqueResult();
        return category;
    }

    @Override
    public Category getCategoryById(Long id) {
        //Session session = sessionFactory.openSession();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Category category = (Category) session.get(Category.class, id);
        session.getTransaction().commit();
        //session.close();
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        SQLQuery q = session.createSQLQuery("select * from category_table");
        q.addEntity(Category.class);
        List<Category> categoryList = (List<Category>) q.list();
        //session.close();
        return categoryList;
    }

    @Override
    public Serializable createCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Serializable id = session.save(category);
        session.getTransaction().commit();
        //session.close();
        return id;
    }

    @Override
    public void removeCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(category);
        session.getTransaction().commit();
        //session.close();
    }

    @Override
    public Category updateCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(category);
        session.getTransaction().commit();
        //session.close();
        return category;
    }
}
