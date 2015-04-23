package runner;

import dao.*;
import model.Author;
import model.Book;
import model.Category;
import model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

/**
 * Created by Malyar on 04.04.2015.
 */
public class Main {

    public static void main(String[] args) {
        //initDatabase();
//        AuthorDao aDao = new AuthorDaoImpl();
//        BookDao bDao = new BookDaoImpl();
//        CategoryDao cDao = new CategoryDaoImpl();
//        OrderDao oDao = new OrderDaoImpl();
//        UserDao uDao = new UserDaoImpl();
    }

    public static void initDatabase() {
        AuthorDao aDao = new AuthorDaoImpl();
        BookDao bDao = new BookDaoImpl();
        CategoryDao cDao = new CategoryDaoImpl();
        OrderDao oDao = new OrderDaoImpl();
        UserDao uDao = new UserDaoImpl();

        User u = new User();
        u.setUserName("admin");
        u.setUserPassword("admin");
        u.setUserPhone("+7******* admin have no phone");
        u.setUserRole("ROLE_ADMIN");
        uDao.createUser(u);

        User u1 = new User();
        u1.setUserName("user");
        u1.setUserPassword("user");
        u1.setUserPhone("+7921******* some phone");
        u1.setUserRole("ROLE_USER");
        uDao.createUser(u1);


        for (int i = 0; i < 10; i++) {
            Book b = new Book();
            b.setBookPrice(255D);
            b.setBookTitle("Book" + (i + 1));
            bDao.createBook(b);
        }

        for (int i = 0; i < 10; i++) {
            Author a = new Author();
            a.setAuthorName("Author" + (i + 1));
            aDao.createAuthor(a);
        }

        for (int i = 0; i < 10; i++) {
            Category c = new Category();
            c.setCategoryName("Category" + (i + 1));
            cDao.createCategory(c);
        }

        for (int i = 0; i < 10; i++) {
            Book b = bDao.getBookById(new Long(i + 1));
            Category c = cDao.getCategoryById(new Long(i + 1));
            Author a = aDao.getAuthorById(new Long(i + 1));
            b.addAuthor(a);
            b.addCategory(c);
            bDao.updateBook(b);
        }

    }
}
