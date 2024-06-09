package tn.org.utss.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.org.utss.entity.Product;
import tn.org.utss.entity.User;
import tn.org.utss.entity.Warehouse;
import tn.org.utss.interfaces.ProductService;
import tn.org.utss.interfaces.ProductWarehouseService;
import tn.org.utss.interfaces.WarehouseService;
import tn.org.utss.repository.ProductRepo;

import java.util.List;

@Service
public class ProductImplementation implements ProductService {

    private final ProductRepo productRepo;


    @Autowired
    public ProductImplementation(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }



}
