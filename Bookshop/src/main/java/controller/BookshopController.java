package controller;

import dao.*;
import model.Book;
import model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.AuthorService;
import service.BookService;
import service.CategoryService;
import service.OrderService;

import java.security.Principal;

/**
 * Created by Malyar on 22.04.2015.
 */
@Controller
public class BookshopController {

    @Autowired
    private AuthorService authorService;
    //private AuthorDao authorService = new AuthorDaoImpl();

    @Autowired
    private BookService bookService;
    //private BookDao bookService = new BookDaoImpl();

    @Autowired
    private CategoryService categoryService;
    //private CategoryDao categoryService = new CategoryDaoImpl();

    @Autowired
    private OrderService orderService;
    //private OrderDao orderService = new OrderDaoImpl();

    @RequestMapping(value = "/bookshop" ,method = RequestMethod.GET)
    public String showBooks(ModelMap model, Principal principal,
                            @RequestParam(value = "authorId", required = false) String authorId) {
        if (principal != null) {
            String name = principal.getName();
            model.addAttribute("username", name);
        }
        if (authorId != null) {
            Long filteredAuthorId = Long.parseLong(authorId);
            if (filteredAuthorId.equals(0L)) {
                model.addAttribute("books", bookService.getAllBooks());
            } else {
                model.addAttribute("books", authorService.getAuthorById(filteredAuthorId).getBookList());
            }
        } else {
            model.addAttribute("books", bookService.getAllBooks());
        }
        model.addAttribute("authors", authorService.getAllAuthors());
        return "bookshop";
    }

    @RequestMapping(value = "/bookshop/order/{id}" , method = RequestMethod.POST)
    public String makeOrder(@PathVariable("id") String bookId, Principal principal) {
        Long orderedBookId = Long.parseLong(bookId);
        Book book = bookService.getBookById(orderedBookId);
        if (principal != null && book != null) {
            Order order = new Order();
            order.setUserName(principal.getName());
            order.setBookId(book.getBookId());
            order.setBookTitle(book.getBookTitle());
            order.setBookPrice(book.getBookPrice());
            order.setOrderStatus("new");
            orderService.createOrder(order);
        }
        return "redirect:/bookshop";
    }
}
