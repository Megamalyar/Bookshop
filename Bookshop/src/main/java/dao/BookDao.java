package dao;

import model.Book;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 05.04.2015.
 */
public interface BookDao {

    public Book getBookByTitle(String title);

    public Book getBookById(Long id);

    public List<Book> getAllBooks();

    public List<Book> getBooksAuthorFiltered(Long authorId);

    public Serializable createBook(Book book);

    public void removeBook(Book book);

    public Book updateBookTitle(Book book, String newTitle);

    public Book updateBook(Book book);
}
