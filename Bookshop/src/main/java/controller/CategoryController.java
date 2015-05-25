package controller;

import dao.BookDao;
import dao.BookDaoImpl;
import dao.CategoryDao;
import dao.CategoryDaoImpl;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.BookService;
import service.CategoryService;

/**
 * Created by Malyar on 15.04.2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    //private CategoryDao categoryService = new CategoryDaoImpl();

    @Autowired
    private BookService bookService;
    //private BookDao bookService = new BookDaoImpl();

    @RequestMapping(value = "/categories" ,method = RequestMethod.GET)
    public String showCategories(ModelMap model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/category/categories";
    }

    @RequestMapping(value = "/categories/new" ,method = RequestMethod.GET)
    public ModelAndView newCategory(ModelMap model) {
        return new ModelAndView("admin/category/new-category", model);
    }

    @RequestMapping(value = "/categories/add" ,method = RequestMethod.POST)
    public String addNewCategory(@RequestParam("name") String name) {
        Category category = new Category();
        category.setCategoryName(name);
        categoryService.createCategory(category);
        return "redirect:/admin/categories";
    }

    @RequestMapping(value = "/categories/delete/{id}" ,method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("id") String categoryId) {
        Category category = categoryService.getCategoryById(Long.parseLong(categoryId));
        categoryService.removeCategory(category);
        return "redirect:/admin/categories";
    }


    @RequestMapping(value = "/categories/edit/update/{id}" ,method = RequestMethod.POST)
    public String updateCategory(@PathVariable("id") String categoryId,
                                 @RequestParam("name") String categoryName,
                                 @RequestParam("bookId") String bookId) {
        Long id = Long.parseLong(categoryId);
        Category category = categoryService.getCategoryById(id);
        category.setCategoryName(categoryName);
        Long bId = Long.parseLong(bookId);
        if (!bId.equals(0L)) {
            category.addBook(bookService.getBookById(bId));
        }
        categoryService.updateCategory(category);
        return "redirect:/admin/categories";
    }

    @RequestMapping(value = "/categories/edit/{id}" ,method = RequestMethod.GET)
    public ModelAndView editCategory(@PathVariable("id") String categoryId, ModelMap model) {
        Category category = categoryService.getCategoryById(Long.parseLong(categoryId));
        model.addAttribute("category", category);
        model.addAttribute("books", bookService.getAllBooks());
        return new ModelAndView("admin/category/category", model);
    }
}
