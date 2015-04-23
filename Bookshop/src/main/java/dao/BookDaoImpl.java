package dao;

import model.Book;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 05.04.2015.
 */
public class BookDaoImpl implements BookDao {

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
    public Serializable createBook(Book book) {
        tx.begin();
        em.persist(book);
        tx.commit();
        return book.getBookId();
    }

    @Override
    public Book getBookByTitle(String title) {
        List<Book> allBooks = em.createQuery(
                "SELECT a FROM Book a WHERE a.bookTitle = '"
                        + title + "'", Book.class).getResultList();
        if (allBooks != null) {
            return allBooks.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Book getBookById(Long id) {
        Book book = em.find(Book.class, id);
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> allBooks = em.createQuery("SELECT a FROM Book a",
                Book.class).getResultList();
        return allBooks;
    }

    @Override
    public List<Book> getBooksAuthorFiltered(Long authorId) {
        List<Book> allBooks = em.createQuery(
                "SELECT a FROM Book a", Book.class).getResultList();
        return allBooks;
    }

    @Override
    public void removeBook(Book book) {
        tx.begin();
        em.remove(book);
        tx.commit();
    }

    @Override
    public Book updateBookTitle(Book book, String newTitle) {
        tx.begin();
        book.setBookTitle(newTitle);
        em.merge(book);
        tx.commit();
        return book;
    }

    public Book updateBook(Book book) {
        tx.begin();
        em.merge(book);
        tx.commit();
        return book;
    }

}
