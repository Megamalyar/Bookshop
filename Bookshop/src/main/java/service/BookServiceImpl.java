package service;

import dao.BookDao;
import model.Book;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 20.05.2015.
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final static Logger log = Logger.getLogger(BookServiceImpl.class);

    @Autowired
    private BookDao bookDao;

    @Override
    public Book getBookByTitle(String title) {
        Book book = bookDao.getBookByTitle(title);
        log.info("book: id = " + book.getBookId() + ", title = "
                + book.getBookTitle() + " chosen");
        return book;
    }

    @Override
    public Book getBookById(Long id) {
        Book book = bookDao.getBookById(id);
        log.info("book: id = " + book.getBookId() + ", title = "
                + book.getBookTitle() + " chosen");
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookDao.getAllBooks();
        for (Book book : books) {
            log.info("book: id = " + book.getBookId() + ", title = "
                    + book.getBookTitle() + " listed");
        }
        return books;
    }

    @Override
    public Serializable createBook(Book book) {
        Serializable book1 = bookDao.createBook(book);
        log.info("book: id = " + book.getBookId() + ", title = "
                + book.getBookTitle() + " created");
        return book1;
    }

    @Override
    public void removeBook(Book book) {
        bookDao.removeBook(book);
        log.info("book: id = " + book.getBookId() + ", title = "
                + book.getBookTitle() + " removed");
    }

    @Override
    public Book updateBook(Book book) {
        Book book1 = bookDao.updateBook(book);
        log.info("book: id = " + book1.getBookId() + ", title = "
                + book1.getBookTitle() + " updated");
        return book1;
    }
}
