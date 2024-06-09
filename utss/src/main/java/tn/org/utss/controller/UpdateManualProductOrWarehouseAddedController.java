package tn.org.utss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.org.utss.interfaces.ProductWarehouseService;

@RestController
@RequestMapping("/uptodate")
@CrossOrigin(origins = "*")
public class UpdateManualProductOrWarehouseAddedController {
    @Autowired
    ProductWarehouseService pwService;

    @GetMapping("productwarehouse")
    public ResponseEntity<String> updateStateProductWarehouse(){
        pwService.upToDateProductsWithAllWarehouses();
        Long count = pwService.upToDateProductsWithAllWarehouses();
        String msg = "";
        msg = (count == 0) ? " no update occured nothing to change" : "update occured we added some rows";

        return ResponseEntity.status(200).body(msg);
    }

}
