package tn.org.utss.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.org.utss.entity.Product;
import tn.org.utss.entity.User;

import java.util.List;

public interface ProductService  {

    List<Product> getAllProducts();

//    Product createProduct(Product product, User connectedUser);
}
