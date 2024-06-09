package tn.org.utss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.org.utss.entity.Product;
import tn.org.utss.entity.User;
import tn.org.utss.entity.Warehouse;
import tn.org.utss.interfaces.ProductService;
import tn.org.utss.interfaces.ProductWarehouseService;
import tn.org.utss.interfaces.WarehouseService;
import tn.org.utss.service.utilData.UserFakeData;

@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = "*")
public class WarehouseAndProductController {
    @Autowired
    ProductWarehouseService pwService;
    @Autowired
    ProductService pService;
    @Autowired
    WarehouseService wService;


    @PostMapping("/warehouse")
    public ResponseEntity<Warehouse> addWarehouse(Warehouse wh ){
        User connUser = UserFakeData.user1agent;
        wh = pwService.CreateWarehouse(wh,connUser);
        return ResponseEntity.status(201).body(wh);
    }


    @PostMapping("/{idSubCategory}/product")
    public ResponseEntity<Product> addProduct(@RequestBody  Product p  , @PathVariable Long idSubCategory){
        User connUser = UserFakeData.user1agent;
        p = pwService.createProduct(p,idSubCategory,connUser);
        return ResponseEntity.status(201).body(p);
    }

}
