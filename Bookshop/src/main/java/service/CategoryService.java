package service;

import model.Category;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Malyar on 19.05.2015.
 */
public interface CategoryService {

    public Category getCategoryByName(String name);

    public Category getCategoryById(Long id);

    public List<Category> getAllCategories();

    public Serializable createCategory(Category category);

    public void removeCategory(Category category);

    public Category updateCategory(Category category);
}
