package tn.org.utss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.org.utss.entity.Category;
import tn.org.utss.interfaces.CategoryService;
import tn.org.utss.repository.CategoryRepo;

import java.util.List;

@Service
public class CategoryImplementationA implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public Category createCategory(Category c){
        return categoryRepo.save(c);
    }


    @Override
    public List<Category> getAllCategories(){return categoryRepo.findAll();}

    /**
     * delete category only if it has no sub categories
     * @param idCategory Long
     * @return Null if category is deleted if it does Return Category this mean category can not be deleted
     */
    @Override
    public Category deleteCategory(Long idCategory){
        Category category = categoryRepo.findById(idCategory).orElse(null);
        if (category != null && category.getSubcategories() != null && category.getSubcategories().isEmpty()) {
            categoryRepo.deleteById( idCategory);
            category = null;
        }
        return category;
    }
}
