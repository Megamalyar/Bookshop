package dao;

import model.Category;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 06.04.2015.
 */
public interface CategoryDao {

    public Category getCategoryByName(String name);

    public Category getCategoryById(Long id);

    public List<Category> getAllCategories();

    public Serializable createCategory(Category category);

    public void removeCategory(Category category);

    public Category updateCategoryName(Category category, String newName);

    public Category updateCategory(Category category);
}
