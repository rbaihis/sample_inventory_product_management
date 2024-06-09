package tn.org.utss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.org.utss.entity.Category;
import tn.org.utss.entity.SubCategory;
import tn.org.utss.interfaces.CategoryService;
import tn.org.utss.interfaces.SubCategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryAndSubCategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubCategoryService subCategoryService;

    //-------Category----------------
    @PostMapping
    ResponseEntity<Category> addCategory(@RequestBody Category c){
        return ResponseEntity.status(201).body(categoryService.createCategory(c));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Category> deleteCategory(@PathVariable Long id){

        return ResponseEntity.status(201).body(categoryService.deleteCategory(id));
    }
    @GetMapping
    ResponseEntity<List<Category>> getAllCategories(){

        return ResponseEntity.status(201).body(categoryService.getAllCategories());
    }


    //---------SubCategory--------------
    @PostMapping("/subcategory")
    ResponseEntity<SubCategory> addSubCategory(@RequestBody SubCategory sc , Long idCategory){
        return ResponseEntity.status(200).body(subCategoryService.createSubCategory(sc,idCategory));
    }

    @DeleteMapping("/subcategory/{id}")
    ResponseEntity<SubCategory> deleteSubCategory(@PathVariable Long id){

        return ResponseEntity.status(200).body(subCategoryService.deleteSubCategory(id));
    }

    @GetMapping("/subcategory")
    ResponseEntity<List<SubCategory>> getAllSubCategories(){

        return ResponseEntity.status(200).body(subCategoryService.getAllSubCategories());
    }

    @GetMapping("/subcategory/{name}")
    ResponseEntity<List<SubCategory>> getSubCategoryByName(@PathVariable String name){
        return ResponseEntity.status(200).body(subCategoryService.findSubCategoryByName(name));
    }
}
