package dao;

import model.Book;
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
public class HibernateBookDao implements BookDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Book getBookByTitle(String title) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        SQLQuery q = session.createSQLQuery("select * from book_table where book_title=:title");
        q.addEntity(Book.class);
        q.setParameter("title", title);
        Book book = (Book) q.uniqueResult();
        return book;
    }

    @Override
    public Book getBookById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Book book = (Book) session.get(Book.class, id);
        session.getTransaction().commit();
        //session.close();
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        SQLQuery q = session.createSQLQuery("select * from book_table");
        q.addEntity(Book.class);
        List<Book> bookList = (List<Book>) q.list();
        //session.close();
        return bookList;
    }

    @Override
    public Serializable createBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Serializable id = session.save(book);
        session.getTransaction().commit();
        //session.close();
        return id;
    }

    @Override
    public void removeBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(book);
        session.getTransaction().commit();
        //session.close();
    }

    @Override
    public Book updateBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(book);
        session.getTransaction().commit();
        //session.close();
        return book;
    }
}
