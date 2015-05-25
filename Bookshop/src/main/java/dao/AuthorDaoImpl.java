package dao;

import model.Author;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 04.04.2015.
 */
public class AuthorDaoImpl implements AuthorDao {

    private static EntityManagerFactory entityManagerFactory;

    @PersistenceContext(unitName = "bookshopUnit")
    private EntityManager em;

    private EntityTransaction tx;

    static {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("bookshopUnit");
        System.out.println(entityManagerFactory.getClass().getSimpleName());
    }

    private void init() {
        em = entityManagerFactory.createEntityManager();
        System.out.println("EntityManager Created : " + em);
        tx = em.getTransaction();
    }

    private void dispose() {
        em.close();
    }

    @Override
    public Author getAuthorByName(String name) {
        init();
        List<Author> allAuthors = em.createQuery(
                "SELECT a FROM Author a WHERE a.authorName = '"
                        + name + "'", Author.class).getResultList();
        if (allAuthors != null) {
            return allAuthors.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Author getAuthorById(Long id) {
        init();
        Author author = em.find(Author.class, id);
        return author;
    }

    @Override
    public List<Author> getAllAuthors() {
        init();
        List<Author> allAuthors = em.createQuery("SELECT a FROM Author a",
                Author.class).getResultList();
        return allAuthors;
    }

    @Override
    public Serializable createAuthor(Author author) {
        init();
        tx.begin();
        em.persist(author);
        tx.commit();
        return author.getAuthorId();
    }

    @Override
    public void removeAuthor(Author author) {
        init();
        tx.begin();
        em.remove(author);
        tx.commit();
    }

    @Override
    public Author updateAuthor(Author author) {
        init();
        tx.begin();
        em.merge(author);
        tx.commit();
        return author;
    }
}
