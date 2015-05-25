package service;

import dao.CategoryDao;
import model.Category;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 19.05.2015.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final static Logger log = Logger.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Category getCategoryByName(String name) {
        Category category = categoryDao.getCategoryByName(name);
        log.info("category: id = " + category.getCategoryId() + ", name = "
                + category.getCategoryName() + " chosen");
        return category;
    }

    @Override
    public Category getCategoryById(Long id) {
        Category category = categoryDao.getCategoryById(id);
        log.info("category: id = " + category.getCategoryId() + ", name = "
                            + category.getCategoryName() + " chosen");
        return category;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryDao.getAllCategories();
        for (Category category : categories) {
            log.info("category: id = " + category.getCategoryId() + ", name = "
                    + category.getCategoryName());
        }
        return categories;
    }

    @Override
    public Serializable createCategory(Category category) {
        Serializable category1 = categoryDao.createCategory(category);
        log.info("category: id = " + category.getCategoryId() + ", name = "
                + category.getCategoryName() + " created");
        return category1;
    }

    @Override
    public void removeCategory(Category category) {
        categoryDao.removeCategory(category);
        log.info("category: id = " + category.getCategoryId() + ", name = "
                + category.getCategoryName() + " removed");
    }

    @Override
    public Category updateCategory(Category category) {
        Category category1 = categoryDao.updateCategory(category);
        log.info("category: id = " + category1.getCategoryId() + ", name = "
                + category1.getCategoryName() + " updated");
        return category1;
    }
}
