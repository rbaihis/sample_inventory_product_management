package tn.org.utss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.org.utss.entity.SubCategory;

import java.util.List;


public interface SubCategoryRepo extends JpaRepository<SubCategory,Long> {
    List<SubCategory> findAllByNameLikeIgnoreCase(String name);

}
