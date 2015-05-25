package dao;

import model.Author;
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
public class HibernateAuthorDao implements AuthorDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Author getAuthorByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        SQLQuery q = session.createSQLQuery("select * from author_table where author_name=:name");
        q.addEntity(Author.class);
        q.setParameter("name", name);
        Author author = (Author) q.uniqueResult();
        return author;
    }

    @Override
    public Author getAuthorById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Author author = (Author) session.get(Author.class, id);
        session.getTransaction().commit();
        //session.close();
        return author;
    }

    @Override
    public List<Author> getAllAuthors() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        SQLQuery q = session.createSQLQuery("select * from author_table");
        q.addEntity(Author.class);
        List<Author> authorList = (List<Author>) q.list();
        //session.close();
        return authorList;
    }

    @Override
    public Serializable createAuthor(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Serializable id = session.save(author);
        session.getTransaction().commit();
        //session.close();
        return id;
    }

    @Override
    public void removeAuthor(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(author);
        session.getTransaction().commit();
        //session.close();
    }

    @Override
    public Author updateAuthor(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(author);
        session.getTransaction().commit();
        //session.close();
        return author;
    }
}
