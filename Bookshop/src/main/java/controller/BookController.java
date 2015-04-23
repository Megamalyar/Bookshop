package controller;

import dao.*;
import model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Malyar on 07.04.2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class BookController {

    //@Autowired
    private BookDao bookDao = new BookDaoImpl();

    private AuthorDao authorDao = new AuthorDaoImpl();

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @RequestMapping(value = "/books" ,method = RequestMethod.GET)
    public String showBooks(ModelMap model) {
        model.addAttribute("books", bookDao.getAllBooks());
        return "admin/book/books";
    }

    @RequestMapping(value = "/books/new" ,method = RequestMethod.GET)
    public ModelAndView newBook(ModelMap model) {
        return new ModelAndView("admin/book/new-book", model);
    }

    @RequestMapping(value = "/books/add" ,method = RequestMethod.POST)
    public String addNewBook(@RequestParam("title") String title,
                             @RequestParam("price") String price) {
        Book book = new Book();
        book.setBookTitle(title);
        book.setBookPrice(Double.parseDouble(price));
        bookDao.createBook(book);
        return "redirect:/admin/books";
    }

    @RequestMapping(value = "/books/delete/{id}" ,method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") String bookId) {
        bookDao.removeBook(bookDao.getBookById(Long.parseLong(bookId)));
        return "redirect:/admin/books";
    }


    @RequestMapping(value = "/books/edit/update/{id}" ,method = RequestMethod.POST)
    public String updateBook(@PathVariable("id") String bookId,
                             @RequestParam("title") String bookTitle,
                             @RequestParam("price") String bookPrice,
                             @RequestParam("authorId") String authorId,
                             @RequestParam("categoryId") String categoryId) {
        Long id = Long.parseLong(bookId);
        Double price = Double.parseDouble(bookPrice);
        Book book = bookDao.getBookById(id);
        book.setBookTitle(bookTitle);
        book.setBookPrice(price);
        Long aId = Long.parseLong(authorId);
        if (!aId.equals(0L)) {
            book.addAuthor(authorDao.getAuthorById(aId));
        }
        Long cId = Long.parseLong(categoryId);
        if (!cId.equals(0L)) {
            book.addCategory(categoryDao.getCategoryById(cId));
        }
        bookDao.updateBook(book);
        return "redirect:/admin/books";
    }

    @RequestMapping(value = "/books/edit/{id}" ,method = RequestMethod.GET)
    public ModelAndView editBook(@PathVariable("id") String bookId, ModelMap model) {
        Book book = bookDao.getBookById(Long.parseLong(bookId));
        model.addAttribute("book", book);
        model.addAttribute("authors", authorDao.getAllAuthors());
        model.addAttribute("categories", categoryDao.getAllCategories());
        return new ModelAndView("admin/book/book", model);
    }

}
