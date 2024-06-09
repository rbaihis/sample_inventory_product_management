package tn.org.utss.interfaces;

import tn.org.utss.entity.SubCategory;

import java.util.List;

public interface SubCategoryService {
    List<SubCategory> getAllSubCategories();

    List<SubCategory> findSubCategoryByName(String name);

    SubCategory createSubCategory(SubCategory c, Long idCategory);

    SubCategory deleteSubCategory(Long idSubCategory);

    SubCategory getSubCategoryById(Long idSubCategory);
}
