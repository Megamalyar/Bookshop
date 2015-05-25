package service;

import dao.AuthorDao;
import model.Author;
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
public class AuthorServiceImpl implements AuthorService {

    private final static Logger log = Logger.getLogger(AuthorServiceImpl.class);
    
    @Autowired
    private AuthorDao authorDao;

    @Override
    public Author getAuthorByName(String name) {
        Author author = authorDao.getAuthorByName(name);
        log.info("author: id = " + author.getAuthorId() + ", name = "
                + author.getAuthorName() + " chosen");
        return author;
    }

    @Override
    public Author getAuthorById(Long id) {
        Author author = authorDao.getAuthorById(id);
        log.info("author: id = " + author.getAuthorId() + ", name = "
                + author.getAuthorName() + " chosen");
        return author;
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = authorDao.getAllAuthors();
        for (Author author : authors) {
            log.info("author: id = " + author.getAuthorId() + ", name = "
                    + author.getAuthorName() + " listed");
        }
        return authors;
    }

    @Override
    public Serializable createAuthor(Author author) {
        Serializable aut = authorDao.createAuthor(author);
        log.info("author: id = " + author.getAuthorId() + ", name = "
                + author.getAuthorName() + " created");
        return aut;
    }

    @Override
    public void removeAuthor(Author author) {
        authorDao.removeAuthor(author);
        log.info("author: id = " + author.getAuthorId() + ", name = "
                + author.getAuthorName() + " removed");
    }

    @Override
    public Author updateAuthor(Author author) {
        Author author1 = authorDao.updateAuthor(author);
        log.info("author: id = " + author1.getAuthorId() + ", name = "
                + author1.getAuthorName() + " updated");
        return author1;
    }
}
