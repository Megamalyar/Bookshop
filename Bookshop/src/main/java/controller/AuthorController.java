package controller;

import dao.AuthorDao;
import dao.AuthorDaoImpl;
import dao.BookDao;
import dao.BookDaoImpl;
import model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.AuthorService;
import service.BookService;

/**
 * Created by Malyar on 14.04.2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    //private AuthorDao authorService = new AuthorDaoImpl();

    @Autowired
    private BookService bookService;
    //private BookDao bookService = new BookDaoImpl();

    @RequestMapping(value = "/authors" ,method = RequestMethod.GET)
    public String showAuthors(ModelMap model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "admin/author/authors";
    }

    @RequestMapping(value = "/authors/new" ,method = RequestMethod.GET)
    public ModelAndView newAuthor(ModelMap model) {
        return new ModelAndView("admin/author/new-author", model);
    }

    @RequestMapping(value = "/authors/add" ,method = RequestMethod.POST)
    public String addNewAuthor(@RequestParam("name") String name) {
        Author author = new Author();
        author.setAuthorName(name);
        authorService.createAuthor(author);
        return "redirect:/admin/authors";
    }

    @RequestMapping(value = "/authors/delete/{id}" ,method = RequestMethod.GET)
    public String deleteAuthor(@PathVariable("id") String authorId) {
        Author author = authorService.getAuthorById(Long.parseLong(authorId));
        authorService.removeAuthor(author);
        return "redirect:/admin/authors";
    }


    @RequestMapping(value = "/authors/edit/update/{id}" ,method = RequestMethod.POST)
    public String updateAuthor(@PathVariable("id") String authorId,
                               @RequestParam("name") String authorName,
                               @RequestParam("bookId") String bookId) {
        Long id = Long.parseLong(authorId);
        Author author = authorService.getAuthorById(id);
        author.setAuthorName(authorName);
        Long bId = Long.parseLong(bookId);
        if (!bId.equals(0L)) {
            author.addBook(bookService.getBookById(bId));
        }
        authorService.updateAuthor(author);
        return "redirect:/admin/authors";
    }

    @RequestMapping(value = "/authors/edit/{id}" ,method = RequestMethod.GET)
    public ModelAndView editAuthor(@PathVariable("id") String authorId, ModelMap model) {
        Author author = authorService.getAuthorById(Long.parseLong(authorId));
        model.addAttribute("author", author);
        model.addAttribute("books", bookService.getAllBooks());
        return new ModelAndView("admin/author/author", model);
    }
}
