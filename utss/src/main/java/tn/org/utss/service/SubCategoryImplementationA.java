package tn.org.utss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.org.utss.entity.SubCategory;
import tn.org.utss.interfaces.SubCategoryService;
import tn.org.utss.repository.CategoryRepo;
import tn.org.utss.repository.SubCategoryRepo;

import java.util.List;

@Service
public class SubCategoryImplementationA implements SubCategoryService {
    @Autowired
    private SubCategoryRepo subCategoryRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<SubCategory> getAllSubCategories(){return subCategoryRepo.findAll();}

    @Override
    public List<SubCategory> findSubCategoryByName(String name){ return subCategoryRepo.findAllByNameLikeIgnoreCase( "%"+name+"%"); }

    @Override
    public SubCategory createSubCategory(SubCategory sc, Long idCategory){
        sc.setCategory(categoryRepo.findById(idCategory).orElse(null));
        return subCategoryRepo.save(sc);
    }

    /**
     * delete SubCategory only if it has no sub categories
     * @param idSubCategory Long
     * @return Null if category is deleted if it does Return SubCategory this mean SubCategory can not be deleted because it has products
     */
    @Override
    public SubCategory deleteSubCategory(Long idSubCategory){
        SubCategory subCat = subCategoryRepo.findById(idSubCategory).orElse(null);
        if (subCat != null && subCat.getProducts() != null && subCat.getProducts().isEmpty()) {
            subCategoryRepo.deleteById( idSubCategory);
            subCat = null;
        }
        return subCat;
    }

    @Override
    public SubCategory getSubCategoryById(Long idSubCategory) {
        return subCategoryRepo.findById(idSubCategory).orElse(null);
    }


}
