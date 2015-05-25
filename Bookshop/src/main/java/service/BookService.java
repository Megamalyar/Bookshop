package service;

import model.Book;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 20.05.2015.
 */
public interface BookService {

    public Book getBookByTitle(String title);

    public Book getBookById(Long id);

    public List<Book> getAllBooks();

    public Serializable createBook(Book book);

    public void removeBook(Book book);

    public Book updateBook(Book book);
}
