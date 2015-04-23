package controller;

import dao.*;
import model.Book;
import model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

/**
 * Created by Malyar on 22.04.2015.
 */
@Controller
public class BookshopController {

    private BookDao bookDao = new BookDaoImpl();

    private AuthorDao authorDao = new AuthorDaoImpl();

    private CategoryDao categoryDao = new CategoryDaoImpl();

    private OrderDao orderDao = new OrderDaoImpl();

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
                model.addAttribute("books", bookDao.getAllBooks());
            } else {
                model.addAttribute("books", authorDao.getAuthorById(filteredAuthorId).getBookList());
            }
        } else {
            model.addAttribute("books", bookDao.getAllBooks());
        }
        model.addAttribute("authors", authorDao.getAllAuthors());
        return "bookshop";
    }

    @RequestMapping(value = "/bookshop/order/{id}" , method = RequestMethod.POST)
    public String makeOrder(@PathVariable("id") String bookId, Principal principal) {
        Long orderedBookId = Long.parseLong(bookId);
        Book book = bookDao.getBookById(orderedBookId);
        if (principal != null && book != null) {
            Order order = new Order();
            order.setUserName(principal.getName());
            order.setBookId(book.getBookId());
            order.setBookTitle(book.getBookTitle());
            order.setBookPrice(book.getBookPrice());
            order.setOrderStatus("new");
            orderDao.createOrder(order);
        }
        return "redirect:/bookshop";
    }
}
