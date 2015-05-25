package service;

import model.Author;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 20.05.2015.
 */
public interface AuthorService {

    public Author getAuthorByName(String name);

    public Author getAuthorById(Long id);

    public List<Author> getAllAuthors();

    public Serializable createAuthor(Author author);

    public void removeAuthor(Author author);

    public Author updateAuthor(Author author);
}
