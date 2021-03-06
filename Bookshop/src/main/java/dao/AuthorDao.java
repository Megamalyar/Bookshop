package dao;

import model.Author;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Malyar on 04.04.2015.
 */
public interface AuthorDao {

    public Author getAuthorByName(String name);

    public Author getAuthorById(Long id);

    public List<Author> getAllAuthors();

    public Serializable createAuthor(Author author);

    public void removeAuthor(Author author);

    public Author updateAuthor(Author author);
    
}
