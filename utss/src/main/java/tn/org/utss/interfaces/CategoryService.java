package tn.org.utss.interfaces;

import tn.org.utss.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category c);

    List<Category> getAllCategories();

    Category deleteCategory(Long idCategory);
}
