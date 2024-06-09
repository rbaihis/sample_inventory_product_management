package tn.org.utss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.org.utss.entity.Product;

public interface ProductRepo  extends JpaRepository<Product,Long> {
}
