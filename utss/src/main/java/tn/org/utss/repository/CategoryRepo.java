package tn.org.utss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.org.utss.entity.Category;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
